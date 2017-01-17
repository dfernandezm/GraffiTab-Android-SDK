package com.graffitabsdk.network.common.response;

/**
 * Created by david on 10/11/2016.
 */
public abstract class GTResponseHandler<T> {
    public abstract void onSuccess(GTResponse<T> gtResponse);
    public abstract void onFailure(GTResponse<T> gtResponse);

    public void onCache(GTResponse<T> gtResponse) {
        // Defaults to empty implementation so that the clients of the SDK do not need to forcibly implement
        // a cache behaviour
    }
}