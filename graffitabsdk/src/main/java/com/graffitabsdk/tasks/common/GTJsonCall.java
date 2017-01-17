package com.graffitabsdk.tasks.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.GTResultCode;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by david on 11/12/2016.
 */

class GTJsonCall<T> extends GTCall<T> {

    private static final Type STRING_MAP_GSON_TYPE = new TypeToken<Map<String, String>>(){}.getType();
    private static final Gson gson = new Gson();
    private final Call<T> wrappedCall;

    private Response<T> resolvedResponse;

    public GTJsonCall(Call<T> wrappedCall,
                      AfterCompletionOperation<T> afterCompletionOperation) {
        super(wrappedCall.request().url().toString(),afterCompletionOperation);
        this.wrappedCall = wrappedCall;
    }

    public void execute(GTResponseHandler<T> responseHandler) {
        wrappedCall.enqueue(getJsonCallback(responseHandler));
    }

    private Callback<T> getJsonCallback(final GTResponseHandler<T> responseHandler) {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                resolvedResponse = response;
                successful = response.isSuccessful();
                handleErrorOrSuccessfulResponse(responseHandler);
                done = true;
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
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
        return resolvedResponse.body();
    }

    private void handleUnsuccessfulCall(Response<T> response,
                                        GTResponseHandler<T> responseHandler) {
        GTResultCode resultCode = GTResultCode.OTHER;
        String resultMessage = null;
        try {
            String jsonError = response.errorBody().string();
            Map<String, String> jsonBody = convertErrorResponseToMap(jsonError);
            String resultCodeString = jsonBody.get("resultCode");
            resultMessage = jsonBody.get("resultMessage");
            resultCode = GTResultCode.valueOf(resultCodeString);
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
                GTResponse.error(GTResultCode.OTHER, resultMessage, apiEndpointUrl);

        runAfterCompletion(gtResponse, false);
        responseHandler.onFailure(gtResponse);
    }

    private Map<String, String> convertErrorResponseToMap(String jsonError) throws IOException {
        return gson.fromJson(jsonError, STRING_MAP_GSON_TYPE);
    }
}
