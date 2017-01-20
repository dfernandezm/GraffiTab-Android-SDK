package com.graffitabsdk.network.common;

import com.graffitabsdk.network.call.GTCall;

/**
 * Wrapper for the Call done in the API
 *
 * Created by david on 10/11/2016.
 */
public class GTRequestPerformed {

    private GTCall<?> requestCall;

    public GTRequestPerformed(GTCall<?> requestCall) {
        this.requestCall = requestCall;
    }
}
