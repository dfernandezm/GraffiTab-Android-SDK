package com.graffitabsdk.tasks.common;

import com.graffitabsdk.network.common.GTResponse;
import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.RequestPerformed;
import retrofit2.Call;

import java.util.Map;


/**
 * Created by david on 09/11/2016.
 */
public abstract class GTNetworkTask<T>  {

    protected RequestPerformed<T> performJsonRequest(Call<Map<String,T>> request, String propertyNameToExtract,
                                                     GTResponseHandler<T> responseHandler) {
        GTCall<T> call = GTCall.jsonCall(request, propertyNameToExtract, getAfterCompletionOperations());
        RequestPerformed<T> requestPerformed = new RequestPerformed<>(call);
        call.execute(responseHandler);
        return requestPerformed;
    }

    protected RequestPerformed<T> performRawRequest(Call<T> request, GTResponseHandler<T> responseHandler) {
        GTCall<T> call = GTCall.rawCall(request, getAfterCompletionOperations());
        RequestPerformed<T> requestPerformed = new RequestPerformed<>(call);
        call.execute(responseHandler);
        return requestPerformed;
    }

    private AfterCompletionOperation<T> getAfterCompletionOperations() {
        return new AfterCompletionOperation<T>() {
            @Override
            public void executeOnSuccess(GTResponse<T> gtResponse) {
                performExtraOperationOnSuccess(gtResponse);
            }

            @Override
            public void executeOnFailure(GTResponse<T> gtResponse) {
                performExtraOperationOnFailure(gtResponse);
            }
        };
    }

    protected void performExtraOperationOnSuccess(GTResponse<T> gtResponse) {
        // Subclasses can provide custom code to execute by the SDK (like save an user, clear cache etc) when the
        // call is successful
    }

    protected void performExtraOperationOnFailure(GTResponse<T> gtResponse) {
        // Subclasses can provide custom code to execute by the SDK (like save an user, clear cache etc)
        // when the call failed
    }
}
