package com.graffitabsdk.network.common;

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

    public Boolean isDone() {
        return requestCall.isDone();
    }
}