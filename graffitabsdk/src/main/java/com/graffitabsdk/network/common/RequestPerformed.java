package com.graffitabsdk.network.common;

import com.graffitabsdk.tasks.common.GTCall;

/**
 * Wrapper for the Call done in the API
 *
 * Created by david on 10/11/2016.
 */
public class RequestPerformed {

    private GTCall<?> requestCall;

    public RequestPerformed(GTCall<?> requestCall) {
        this.requestCall = requestCall;
    }
}
