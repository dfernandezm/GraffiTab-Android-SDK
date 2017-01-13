package com.graffitabsdk.tasks.common;

import com.graffitabsdk.network.common.GTResponse;
import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.tasks.cache.GTCacheService;

import java.util.Map;

import retrofit2.Call;


/**
 * Created by david on 09/11/2016.
 */
public abstract class GTNetworkTask<T> {

    protected GTCacheService cacheService;

    protected RequestPerformed<T> performJsonRequest(Call<Map<String,T>> request, Class<T> type, String propertyNameToExtract,
                                                     GTResponseHandler<T> responseHandler, boolean shouldUseCache) {
        boolean isGetRequest = request.request().method().equalsIgnoreCase("GET");
        AfterCompletionOperation<T> afterCompletionOperation = getAfterCompletionOperations(shouldUseCache, isGetRequest);
        GTCall<T> call = GTCall.jsonCall(request, propertyNameToExtract, afterCompletionOperation);
        RequestPerformed<T> requestPerformed = new RequestPerformed<>(call);

        // Try to resolve from cache
        resolveCallFromCacheIfPossible(call.getApiEndpointUrl(), responseHandler, shouldUseCache, isGetRequest, type);

        // The network call is executed anyway
        call.execute(responseHandler);
        return requestPerformed;
    }

    /**
     * Requests that do not get root JSON object as a response. These responses are never cached
     * @param request
     * @param responseHandler
     * @return
     */
    protected RequestPerformed<T> performRawRequest(Call<T> request, GTResponseHandler<T> responseHandler) {
        GTCall<T> call = GTCall.rawCall(request, getAfterCompletionOperations(false, false));
        RequestPerformed<T> requestPerformed = new RequestPerformed<>(call);
        call.execute(responseHandler);
        return requestPerformed;
    }

    private AfterCompletionOperation<T> getAfterCompletionOperations(final boolean shouldUseCache, final boolean isGetRequest) {
        return new AfterCompletionOperation<T>() {
            @Override
            public void executeOnSuccess(GTResponse<T> gtResponse) {
                saveResponseToCacheIfPossible(gtResponse, shouldUseCache, isGetRequest);
                performExtraOperationOnSuccess(gtResponse);
            }

            @Override
            public void executeOnFailure(GTResponse<T> gtResponse) {
                performExtraOperationOnFailure(gtResponse);
            }
        };
    }

    protected void performExtraOperationOnSuccess(GTResponse<T> gtResponse) {
        // Subclasses can provide custom code to execute by the SDK (like save an user, clear cache etc) when the
        // call is successful
    }

    protected void performExtraOperationOnFailure(GTResponse<T> gtResponse) {
        // Subclasses can provide custom code to execute by the SDK (like save an user, clear cache etc)
        // when the call failed
    }

    private void resolveCallFromCacheIfPossible(String apiEndpointUrl, GTResponseHandler<T> responseHandler,
                                            boolean requestShouldUseCache, boolean isGetRequest, Class<T> type) {
        if (requestShouldUseCache && isGetRequest) {

            if (cacheService == null) {
                throw new IllegalStateException("Request requiring cache provides a null cacheService");
            }

            GTResponse<T> gtResponse = cacheService.resolveRequestFromCache(apiEndpointUrl, type);
            if (gtResponse.getObject() != null) {
                // Cache hit, execute cache handler on the app side -- only execute the handler in case of a cache hit
                responseHandler.onCache(gtResponse);
            }
            // if there is a cache miss, go ahead like there were no cache
        }
    }

    private void saveResponseToCacheIfPossible(GTResponse<T> gtResponse, boolean shouldUseCache, boolean isGetRequest) {
        if (shouldUseCache && isGetRequest) {
            if (cacheService == null) {
                throw new IllegalStateException("Request requiring cache provides a null cacheService");
            }
            cacheService.saveResponseToCache(gtResponse);
        }
    }
}
