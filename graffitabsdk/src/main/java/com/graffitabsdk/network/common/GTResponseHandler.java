package com.graffitabsdk.network.common;

/**
 * Created by david on 10/11/2016.
 */

// Abstract class in preparation for cache
public abstract class GTResponseHandler<T> {
    public abstract void onSuccess(GTResponse<T> gtResponse);
    public abstract void onFailure(GTResponse<T> responseObject);
}