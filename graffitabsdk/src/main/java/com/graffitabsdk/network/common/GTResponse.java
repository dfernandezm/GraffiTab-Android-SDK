package com.graffitabsdk.network.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by david on 09/11/2016.
 */
@Getter
@Setter
public class GTResponse<T> {
    private ResultCode resultCode;
    private String resultDetail;
    private Integer statusCode;
    private T object;
    private String apiEndpointUrl;

    public GTResponse(T object) {
        this.object = object;
        this.resultCode = ResultCode.OK;
        this.statusCode = 200;
    }

    public GTResponse(T object, ResultCode resultCode) {
        this.object = object;
        this.resultCode = resultCode;
        this.statusCode = resultCode.getStatusCode();
    }

    public GTResponse() {}

    public static <T> GTResponse<T> error(ResultCode resultCode, String errorMessage,
                                           String apiEndpointUrl) {
        GTResponse<T> response = new GTResponse<T>();
        response.setResultCode(resultCode);
        response.setStatusCode(resultCode.getStatusCode());
        response.setApiEndpointUrl(apiEndpointUrl);
        response.setResultDetail(errorMessage);
        return response;
    }

    public static <T> GTResponse<T> error(Integer statusCode, String errorMessage,
                                          String apiEndpointUrl) {
        GTResponse<T> response = new GTResponse<T>();
        response.setResultCode(ResultCode.EMPTY);
        response.setStatusCode(statusCode);
        response.setApiEndpointUrl(apiEndpointUrl);
        response.setResultDetail(errorMessage);
        return response;
    }

}
