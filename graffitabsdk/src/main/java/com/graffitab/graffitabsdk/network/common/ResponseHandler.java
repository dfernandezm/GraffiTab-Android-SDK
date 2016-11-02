package com.graffitab.graffitabsdk.network.common;

import com.graffitab.graffitabsdk.network.common.GTResponseObject;

/**
 * Created by david on 10/11/2016.
 */

public interface ResponseHandler<T> {
    void onSuccess(GTResponseObject<T> responseObject);
    void onFailure(GTResponseObject<T> responseObject);
}
