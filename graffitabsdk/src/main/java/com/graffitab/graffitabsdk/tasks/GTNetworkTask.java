package com.graffitab.graffitabsdk.tasks;

import com.graffitab.graffitabsdk.network.common.GTResponseObject;
import com.graffitab.graffitabsdk.network.common.RequestPerformed;
import com.graffitab.graffitabsdk.network.common.ResponseHandler;
import com.graffitab.graffitabsdk.network.common.GTCall;
import com.graffitab.graffitabsdk.network.common.ResultCode;

import java.util.Map;
import java.util.concurrent.Callable;

import retrofit2.Call;

/**
 * Created by david on 09/11/2016.
 */
public abstract class GTNetworkTask<T> {

    protected ResponseHandler<T> responseHandler;
    protected String propertyNameToExtract;

    public GTNetworkTask() {}

    protected RequestPerformed<T> performRequest(Call<Map<String,T>> request)  {
        try {
            GTCall<T> call = wrap(request);
            RequestPerformed<T> requestPerformed = new RequestPerformed<>(call);
            call.execute(responseHandler, propertyNameToExtract);
            return requestPerformed;
        } catch (Exception e) {
            //TODO: log the exception
            String apiEndpointUrl = request.request().url().toString();
            responseHandler.onFailure(GTResponseObject.error(ResultCode.OTHER,
                                                        e.getMessage(), apiEndpointUrl));
            return null;
        }
    }

    private GTCall<T> wrap(Call<Map<String,T>> call) {
        return new GTCall<>(call);
    }
}
