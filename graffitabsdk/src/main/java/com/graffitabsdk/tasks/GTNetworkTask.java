package com.graffitabsdk.tasks;

import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.network.common.GTCall;

import java.util.Map;

import retrofit2.Call;

/**
 * Created by david on 09/11/2016.
 */
public abstract class GTNetworkTask<T> {

    protected GTResponseHandler<T> responseHandler;
    protected String propertyNameToExtract;

    public GTNetworkTask() {}

    protected RequestPerformed<T> performRequest(Call<Map<String, T>> request) {
        GTCall<T> call = wrap(request);
        RequestPerformed<T> requestPerformed = new RequestPerformed<>(call);
        call.execute(responseHandler, propertyNameToExtract);
        return requestPerformed;
    }

    protected void setResponseProperties(GTResponseHandler<T> responseHandler, String propertyName) {
        this.propertyNameToExtract = propertyName;
        this.responseHandler = responseHandler;
    }

    private GTCall<T> wrap(Call<Map<String,T>> call) {
        return new GTCall<>(call);
    }

}
