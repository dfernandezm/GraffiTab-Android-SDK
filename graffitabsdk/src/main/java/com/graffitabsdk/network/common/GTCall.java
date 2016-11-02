package com.graffitabsdk.network.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by david on 22/11/2016.
 */
public class GTCall<T> {

    private final Call<Map<String,T>> wrappedCall;

    private final String apiEndpointUrl;

    private final Gson gson = new Gson();

    private Boolean done = Boolean.FALSE;

    public GTCall(Call<Map<String,T>> wrappedCall) {
        this.wrappedCall = wrappedCall;
        this.apiEndpointUrl = wrappedCall.request().url().toString();
    }

    public void execute(GTResponseHandler<T> responseHandler, String propertyToExtract) {
        wrappedCall.enqueue(getCallback(responseHandler, propertyToExtract));
    }

    public Boolean isDone() {
        return done;
    }

    public String getApiEndpointUrl() {
        return apiEndpointUrl;
    }

    private Callback<Map<String, T>> getCallback(final GTResponseHandler<T> responseHandler,
                                                 final String propertyNameToExtract) {
        return new Callback<Map<String, T>>() {
            @Override
            public void onResponse(Call<Map<String, T>> call, Response<Map<String, T>> response) {
                handleErrorOrSuccessfulResponse(call, response, responseHandler, propertyNameToExtract);
                done = true;
            }

            @Override
            public void onFailure(Call<Map<String, T>> call, Throwable t) {
               handleOtherFailure(call, t.getMessage(), responseHandler);
                done = true;
            }
        };
    }

    /**
     * onResponse is called when any kind of response has been received.
     */
    private void handleErrorOrSuccessfulResponse(Call<Map<String, T>> call,
                                                 Response<Map<String, T>> response,
                                                 GTResponseHandler<T> responseHandler,
                                                 String propertyNameToExtract) {
        // isSuccess is true if response code => 200 and <= 300
        if (response.isSuccessful()) {
            handleSuccessfulResponse(response, propertyNameToExtract, call, responseHandler);
        } else {
           handleErroredCall(response, responseHandler);
        }
    }

    private void handleUnsuccessfulCall(Response<Map<String, T>> response,
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
                                          Call<Map<String, T>> call,
                                          GTResponseHandler<T> responseHandler) {
        T decodedResponse = (T) response.body().get(propertyNameToExtract);
        GTResponse<T> responseObject = new GTResponse<T>();
        responseObject.setObject(decodedResponse);
        responseObject.setResultCode(ResultCode.OK);
        responseObject.setApiEndpointUrl(call.request().url().toString());
        responseHandler.onSuccess(responseObject);
    }

    private void handleErroredCall(Response<Map<String, T>> response,
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
    private void handleOtherFailure(Call<Map<String,T>> call, String errorMessage,
                                    GTResponseHandler<T> responseHandler) {
        GTResponse<T> responseObject = new GTResponse<T>();
        responseObject.setObject(null);
        responseObject.setResultDetail(errorMessage);
        responseObject.setResultCode(ResultCode.OTHER);
        responseObject.setApiEndpointUrl(call.request().url().toString());
        responseHandler.onFailure(responseObject);
    }

    private Map<String, String> convertErrorResponseToMap(String jsonError) throws IOException {
        //TODO: extract this as it is constant
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        return gson.fromJson(jsonError, type);
    }
}
