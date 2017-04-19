package com.graffitabsdk.network.call;

import com.graffitabsdk.network.common.response.GTResponse;

/**
 * Created by david on 11/12/2016.
 */

interface GTAfterCompletionOperation<T> {
    void executeOnSuccess(GTResponse<T> gtResponse);
    void executeOnFailure(GTResponse<T> gtResponse);
}
