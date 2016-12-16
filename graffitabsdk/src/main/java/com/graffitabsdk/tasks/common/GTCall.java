package com.graffitabsdk.tasks.common;

import com.graffitabsdk.network.common.GTResponse;
import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.ResultCode;
import retrofit2.Call;

import java.util.Map;

/**
 * Created by david on 22/11/2016.
 */
public abstract class GTCall<T> {

    protected final String apiEndpointUrl;
    protected Boolean done  = Boolean.FALSE;
    protected Boolean successful = Boolean.FALSE;
    protected AfterCompletionOperation<T> afterCompletionOperation;

    protected GTCall(String apiEndpointUrl, AfterCompletionOperation<T> afterCompletion) {
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

        gtResponse.setResultCode(ResultCode.OK);
        gtResponse.setApiEndpointUrl(apiEndpointUrl);

        runAfterCompletion(gtResponse, true);
        responseHandler.onSuccess(gtResponse);
    }

    /**
     * onFailure gets called when the HTTP request didn't get through.
     * For instance if the URL is invalid / host not reachable
     */
    protected void handleOtherFailure(String errorMessage, GTResponseHandler<T> responseHandler) {
        GTResponse<T> gtResponse = GTResponse.error(ResultCode.OTHER, errorMessage, apiEndpointUrl);
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

    static <T> GTCall<T> jsonCall(Call<Map<String,T>> call, String propertyNameToExtract,
                                         AfterCompletionOperation<T> afterCompletionOperation) {
        return new GTJsonCall<>(call, propertyNameToExtract, afterCompletionOperation);
    }

    static <T> GTCall<T> rawCall(Call<T> call, AfterCompletionOperation<T> afterCompletionOperation) {
        return new GTRawCall<>(call, afterCompletionOperation);
    }
}
