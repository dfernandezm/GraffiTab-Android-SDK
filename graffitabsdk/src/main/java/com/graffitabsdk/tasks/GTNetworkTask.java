package com.graffitabsdk.tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.graffitabsdk.network.common.GTCall;
import com.graffitabsdk.network.common.GTResponse;
import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.network.common.ResultCode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by david on 09/11/2016.
 */
public abstract class GTNetworkTask<T>  {

    protected GTResponseHandler<T> responseHandler;
    protected String apiEndpointUrl;
    protected Boolean done = Boolean.FALSE;

    protected static final Type STRING_MAP_GSON_TYPE = new TypeToken<Map<String, String>>(){}.getType();
    protected String propertyNameToExtract;
    protected Gson gson;

    protected RequestPerformed<T> performRequest(Call<Map<String,T>> request) {
        GTCall<T> call = wrap(request);
        this.apiEndpointUrl = call.getApiEndpointUrl();
        RequestPerformed<T> requestPerformed = new RequestPerformed<>(call);
        call.execute(getJsonCallback(responseHandler, propertyNameToExtract));
        return requestPerformed;
    }

    protected RequestPerformed<T> performRawRequest(Call<T> request) {

        this.apiEndpointUrl = request.request().url().toString();
        request.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                GTResponse<T> gtResponse = new GTResponse<T>();
                gtResponse.setApiEndpointUrl(apiEndpointUrl);

                if (response.isSuccessful()) {
                    gtResponse.setResultCode(ResultCode.OK);
                    performExtraOperationOnSuccess(gtResponse);
                    responseHandler.onSuccess(gtResponse);
                } else {
                    try {
                        gtResponse = GTResponse.error(response.code(), response.errorBody().string(), apiEndpointUrl);
                    } catch (IOException e) {
                        gtResponse = GTResponse.error(response.code(), e.getMessage(), apiEndpointUrl);
                    }
                    performExtraOperationOnFailure(gtResponse);
                    responseHandler.onFailure(gtResponse);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
              handleOtherFailure(t.getMessage(), responseHandler);
            }
        });

        return new RequestPerformed<>(null);
    }

    protected void setCommonResponseProperties(GTResponseHandler<T> responseHandler, String propertyName) {
        this.propertyNameToExtract = propertyName;
        this.responseHandler = responseHandler;
    }

    private GTCall<T> wrap(Call<Map<String,T>> call) {
        return new GTCall<>(call, this);
    }

    private Callback<Map<String,T>> getJsonCallback(final GTResponseHandler<T> responseHandler,
                                                    final String propertyNameToExtract) {
        return new Callback<Map<String,T>>() {
            @Override
            public void onResponse(Call<Map<String,T>> call, Response<Map<String,T>> response) {
                handleErrorOrSuccessfulResponse(call, response, responseHandler, propertyNameToExtract);
                done = true;
            }

            @Override
            public void onFailure(Call<Map<String,T>> call, Throwable t) {
                handleOtherFailure(t.getMessage(), responseHandler);
                done = true;
            }
        };
    }

    /**
     * onResponse is called when any kind of response has been received.
     */
    private void handleErrorOrSuccessfulResponse(Call<Map<String,T>> call,
                                                 Response<Map<String,T>> response,
                                                 GTResponseHandler<T> responseHandler,
                                                 String propertyNameToExtract) {
        // isSuccess is true if response code => 200 and <= 300
        if (response.isSuccessful()) {
            handleSuccessfulResponse(response, propertyNameToExtract, call, responseHandler);
        } else {
            handleErroredCall(response, responseHandler);
        }
    }

    private void handleUnsuccessfulCall(Response<Map<String,T>> response,
                                        GTResponseHandler<T> responseHandler) {
        ResultCode resultCode = ResultCode.OTHER;
        String resultMessage = null;
        try {
            String jsonError = response.errorBody().string();
            Map<String, String> jsonBody = convertErrorResponseToMap(jsonError);
            String resultCodeString = jsonBody.get("resultCode");
            resultMessage = jsonBody.get("resultMessage");
            resultCode = ResultCode.valueOf(resultCodeString);
        } catch (IOException e) {
            resultMessage = e.getMessage();
        } finally {
            GTResponse<T> responseObject =
                    GTResponse.error(resultCode, resultMessage, apiEndpointUrl);
            responseHandler.onFailure(responseObject);
        }
    }

    private void handleInvalidJsonResponse(GTResponseHandler<T> responseHandler) {
        String resultMessage = "Invalid JSON detected in response";
        GTResponse<T> responseObject =
                GTResponse.error(ResultCode.OTHER, resultMessage, apiEndpointUrl);
        responseHandler.onFailure(responseObject);
    }

    private void handleSuccessfulResponse(Response<Map<String,T>> response,
                                          String propertyNameToExtract,
                                          Call<Map<String,T>> call,
                                          GTResponseHandler<T> responseHandler) {

        GTResponse<T> gtResponse = new GTResponse<T>();

        T decodedResponse = (T) response.body().get(propertyNameToExtract);
        gtResponse.setObject(decodedResponse);

        gtResponse.setResultCode(ResultCode.OK);
        gtResponse.setApiEndpointUrl(call.request().url().toString());
        responseHandler.onSuccess(gtResponse);
        performExtraOperationOnSuccess(gtResponse);
    }

    private void handleErroredCall(Response<Map<String,T>> response,
                                   GTResponseHandler<T> responseHandler) {
        if (response.body() == null && response.errorBody() == null) {
            handleInvalidJsonResponse(responseHandler);
        } else {
            handleUnsuccessfulCall(response, responseHandler);
        }
    }

    /**
     * onFailure gets called when the HTTP request didn't get through.
     * For instance if the URL is invalid / host not reachable
     */
    private void handleOtherFailure(String errorMessage, GTResponseHandler<T> responseHandler) {
        GTResponse<T> gtResponse = GTResponse.error(ResultCode.OTHER, errorMessage, apiEndpointUrl);
        responseHandler.onFailure(gtResponse);
    }

    private Map<String, String> convertErrorResponseToMap(String jsonError) throws IOException {
        return gson.fromJson(jsonError, STRING_MAP_GSON_TYPE);
    }

    protected void performExtraOperationOnSuccess(GTResponse<T> gtResponse) {
        // Subclasses can provide custom code to execute by the SDK (like save an user, clear cache etc) when the
        // call is successful
    }

    protected void performExtraOperationOnFailure(GTResponse<T> gtResponse) {
        // Subclasses can provide custom code to execute by the SDK (like save an user, clear cache etc)
        // when the call failed
    }
}
