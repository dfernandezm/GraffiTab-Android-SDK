package com.graffitabsdk.tasks.common;

import com.google.gson.Gson;
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

    public String getApiEndpointUrl() {
        return apiEndpointUrl;
    }

    public abstract void execute(GTResponseHandler<T> responseHandler);

    abstract void handleSuccessfulResponse(GTResponseHandler<T> responseHandler);

    abstract void handleErrorResponse(GTResponseHandler<T> responseHandler);

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

    public static <T> GTCall<T> jsonCall(Call<Map<String,T>> call, String propertyNameToExtract, Gson gson,
                                         AfterCompletionOperation<T> afterCompletionOperation) {
        return new GTJsonCall<>(call, propertyNameToExtract, gson, afterCompletionOperation);
    }

    public static <T> GTCall<T> rawCall(Call<T> call, AfterCompletionOperation<T> afterCompletionOperation) {
        return new GTRawCall<>(call, afterCompletionOperation);
    }
}
