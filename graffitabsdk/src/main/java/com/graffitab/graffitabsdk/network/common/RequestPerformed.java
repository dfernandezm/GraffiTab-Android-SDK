package com.graffitab.graffitabsdk.network.common;

import com.graffitab.graffitabsdk.network.common.GTCall;

/**
 * Wrapper for the Call done by Retrofit
 *
 * Created by david on 10/11/2016.
 */
public class RequestPerformed<T> {

    private GTCall<T> requestCall;

    public RequestPerformed(GTCall<T> requestCall) {
        this.requestCall = requestCall;
    }

//    public void cancel() {
//        requestCall.cancel();
//    }
//
//    public boolean isCancelled() {
//        return requestCall.isCanceled();
//    }
}
