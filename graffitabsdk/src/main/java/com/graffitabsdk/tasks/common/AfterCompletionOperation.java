package com.graffitabsdk.tasks.common;

import com.graffitabsdk.network.common.GTResponse;

/**
 * Created by david on 11/12/2016.
 */

interface AfterCompletionOperation<T> {
    void executeOnSuccess(GTResponse<T> gtResponse);
    void executeOnFailure(GTResponse<T> gtResponse);
}
