package com.graffitabsdk.network.common;

import com.graffitabsdk.tasks.GTNetworkTask;
import retrofit2.Call;
import retrofit2.Callback;

import java.util.Map;

/**
 * Created by david on 22/11/2016.
 */
public class GTCall<T> {

    private final GTNetworkTask<T> networkTask;
    private final Call<Map<String,T>> wrappedCall;
    private final String apiEndpointUrl;

    public GTCall(Call<Map<String,T>> wrappedCall, GTNetworkTask<T> networkTask) {
        this.wrappedCall = wrappedCall;
        this.networkTask = networkTask;
        this.apiEndpointUrl = wrappedCall.request().url().toString();
    }

    public void execute(Callback<Map<String,T>> callback) {
        wrappedCall.enqueue(callback);
    }

    public String getApiEndpointUrl() {
        return apiEndpointUrl;
    }

}
