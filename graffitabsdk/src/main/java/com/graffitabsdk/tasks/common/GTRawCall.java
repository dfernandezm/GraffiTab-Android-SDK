package com.graffitabsdk.tasks.common;

import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by david on 11/12/2016.
 */

class GTRawCall<T> extends GTCall<T> {

    private final Call<T> wrappedCall;
    private Response<T> resolvedResponse;

    public GTRawCall(Call<T> call, AfterCompletionOperation<T> afterCompletionOperation) {
        super(call.request().url().toString(), afterCompletionOperation);
        this.wrappedCall = call;
    }

    @Override
    public void execute(GTResponseHandler<T> responseHandler) {
        resolvedResponse = null;
        wrappedCall.enqueue(getCallback(responseHandler));
    }

    private Callback<T> getCallback(final GTResponseHandler<T> responseHandler) {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                resolvedResponse = response;
                successful = response.isSuccessful();
                if (response.isSuccessful()) {
                    handleSuccessfulResponse(responseHandler);
                } else {
                    handleErrorResponse(responseHandler);
                }
                done = true;
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                successful = false;
                resolvedResponse = null;
                handleOtherFailure(t.getMessage(), responseHandler);
                done = true;
            }
        };
    }

    @Override
    protected T decodeResponse() {
        return resolvedResponse.body();
    }

    @Override
    void handleErrorResponse(GTResponseHandler<T> responseHandler) {
        GTResponse<T> gtResponse = new GTResponse<T>();
        gtResponse.setApiEndpointUrl(apiEndpointUrl);
        try {
            gtResponse = GTResponse.error(resolvedResponse.code(), resolvedResponse.errorBody().string(), apiEndpointUrl);
        } catch (IOException e) {
            gtResponse = GTResponse.error(resolvedResponse.code(), e.getMessage(), apiEndpointUrl);
        }

        runAfterCompletion(gtResponse, false);
        responseHandler.onFailure(gtResponse);
    }
}
