package com.graffitabsdk.tasks.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.graffitabsdk.network.common.GTResponse;
import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.ResultCode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by david on 11/12/2016.
 */

class GTJsonCall<T> extends GTCall<T> {

    protected static final Type STRING_MAP_GSON_TYPE = new TypeToken<Map<String, String>>(){}.getType();
    private final Call<Map<String,T>> wrappedCall;
    private final String propertyNameToExtract;
    private Gson gson;
    private Response<Map<String,T>> resolvedResponse;

    @Inject
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public GTJsonCall(Call<Map<String,T>> wrappedCall, String propertyNameToExtract,
                      AfterCompletionOperation<T> afterCompletionOperation) {
        super(wrappedCall.request().url().toString(),afterCompletionOperation);
        this.wrappedCall = wrappedCall;
        this.propertyNameToExtract = propertyNameToExtract;
    }

    public void execute(GTResponseHandler<T> responseHandler) {
        wrappedCall.enqueue(getJsonCallback(responseHandler));
    }

    private Callback<Map<String,T>> getJsonCallback(final GTResponseHandler<T> responseHandler) {
        return new Callback<Map<String,T>>() {
            @Override
            public void onResponse(Call<Map<String,T>> call, Response<Map<String,T>> response) {
                resolvedResponse = response;
                successful = response.isSuccessful();
                handleErrorOrSuccessfulResponse(responseHandler);
                done = true;
            }

            @Override
            public void onFailure(Call<Map<String,T>> call, Throwable t) {
                handleOtherFailure(t.getMessage(), responseHandler);
                done = true;
            }
        };
    }

    @Override
    void handleErrorResponse(GTResponseHandler<T> responseHandler) {
        if (resolvedResponse.body() == null && resolvedResponse.errorBody() == null) {
            handleInvalidJsonResponse(responseHandler);
        } else {
            handleUnsuccessfulCall(resolvedResponse, responseHandler);
        }
    }

    /**
     * onResponse is called when any kind of response has been received.
     */
    private void handleErrorOrSuccessfulResponse(GTResponseHandler<T> responseHandler) {
        // isSuccess is true if response code => 200 and <= 300
        if (resolvedResponse.isSuccessful()) {
            handleSuccessfulResponse(responseHandler);
        } else {
            handleErrorResponse(responseHandler);
        }
    }

    @Override
    protected T decodeResponse() {
        return (T) resolvedResponse.body().get(propertyNameToExtract);
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
            //TODO: log this exception
            resultMessage = e.getMessage();
        } finally {
            GTResponse<T> responseObject =
                    GTResponse.error(resultCode, resultMessage, super.apiEndpointUrl);

            runAfterCompletion(responseObject, false);
            responseHandler.onFailure(responseObject);
        }
    }

    private void handleInvalidJsonResponse(GTResponseHandler<T> responseHandler) {
        String resultMessage = "Invalid JSON detected in response";
        GTResponse<T> gtResponse =
                GTResponse.error(ResultCode.OTHER, resultMessage, apiEndpointUrl);

        runAfterCompletion(gtResponse, false);
        responseHandler.onFailure(gtResponse);
    }

    private Map<String, String> convertErrorResponseToMap(String jsonError) throws IOException {
        return gson.fromJson(jsonError, STRING_MAP_GSON_TYPE);
    }
}
