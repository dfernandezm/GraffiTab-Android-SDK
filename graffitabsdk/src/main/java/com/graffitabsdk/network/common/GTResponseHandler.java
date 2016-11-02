package com.graffitabsdk.network.common;

/**
 * Created by david on 10/11/2016.
 */

public interface GTResponseHandler<T> {
    void onSuccess(GTResponse<T> gtResponse);
    void onFailure(GTResponse<T> responseObject);
}
