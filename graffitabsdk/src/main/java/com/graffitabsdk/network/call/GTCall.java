package com.graffitabsdk.network.call;

import com.graffitabsdk.network.common.GTResultCode;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;

import retrofit2.Call;

/**
 * Created by david on 22/11/2016.
 */
public abstract class GTCall<T> {

    protected final String apiEndpointUrl;
    protected Boolean done  = Boolean.FALSE;
    protected Boolean successful = Boolean.FALSE;
    protected GTAfterCompletionOperation<T> afterCompletionOperation;

    protected GTCall(String apiEndpointUrl, GTAfterCompletionOperation<T> afterCompletion) {
        this.apiEndpointUrl = apiEndpointUrl;
        this.afterCompletionOperation = afterCompletion;
    }

    abstract T decodeResponse();

    abstract void handleErrorResponse(GTResponseHandler<T> responseHandler);

    public String getApiEndpointUrl() {
        return apiEndpointUrl;
    }

    public abstract void execute(GTResponseHandler<T> responseHandler);

    protected void handleSuccessfulResponse(GTResponseHandler<T> responseHandler) {
        GTResponse<T> gtResponse = new GTResponse<T>();
        T decodedResponse = decodeResponse();
        gtResponse.setObject(decodedResponse);
        gtResponse.setIsSuccessful(true);

        gtResponse.setResultCode(GTResultCode.OK);
        gtResponse.setApiEndpointUrl(apiEndpointUrl);

        runAfterCompletion(gtResponse, true);
        responseHandler.onSuccess(gtResponse);
    }

    /**
     * onFailure gets called when the HTTP request didn't get through.
     * For instance if the URL is invalid / host not reachable
     */
    protected void handleOtherFailure(String errorMessage, GTResponseHandler<T> responseHandler) {
        GTResponse<T> gtResponse = GTResponse.error(GTResultCode.OTHER, errorMessage, apiEndpointUrl);
        responseHandler.onFailure(gtResponse);
        runAfterCompletion(gtResponse, false);
    }

    public Boolean isDone() {
        return done;
    }

    public boolean isSuccessful() {
        return successful;
    }

    protected void runAfterCompletion(GTResponse<T> gtResponse, Boolean success) {
        if (afterCompletionOperation != null) {
            if (success) {
                afterCompletionOperation.executeOnSuccess(gtResponse);
            } else {
                afterCompletionOperation.executeOnFailure(gtResponse);
            }
        }
    }

    static <T> GTCall<T> jsonCall(Call<T> call, GTAfterCompletionOperation<T> afterCompletionOperation) {
        return new GTJsonCall<>(call, afterCompletionOperation);
    }

    static <T> GTCall<T> rawCall(Call<T> call, GTAfterCompletionOperation<T> afterCompletionOperation) {
        return new GTRawCall<>(call, afterCompletionOperation);
    }
}
