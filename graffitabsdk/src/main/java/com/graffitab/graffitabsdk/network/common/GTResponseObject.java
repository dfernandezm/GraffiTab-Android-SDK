package com.graffitab.graffitabsdk.network.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by david on 09/11/2016.
 */
@Getter
@Setter
public class GTResponseObject<T> {
    private ResultCode resultCode;
    private String resultDetail;
    private T object;
    private String apiEndpointUrl;

    public GTResponseObject(T object) {
        this.object = object;
        this.resultCode = ResultCode.OK;
    }

    public GTResponseObject(T object, ResultCode resultCode) {
        this.object = object;
        this.resultCode = resultCode;
    }

    public GTResponseObject() {}

    public static <T> GTResponseObject<T> error(ResultCode resultCode, String errorMessage,
                                             String apiEndpointUrl) {
        GTResponseObject<T> responseObject = new GTResponseObject<>();
        responseObject.setResultCode(resultCode);
        responseObject.setApiEndpointUrl(apiEndpointUrl);
        responseObject.setResultDetail(errorMessage);
        return responseObject;
    }
}
