package com.graffitabsdk.network.common.response;

import com.graffitabsdk.network.common.GTResultCode;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by david on 09/11/2016.
 */
@Getter
@Setter
public class GTResponse<T> {
    private GTResultCode resultCode;
    private String resultDetail;
    private Integer statusCode;
    private T object;
    private String apiEndpointUrl;
    private Boolean isSuccessful;

    public GTResponse(T object) {
        this.object = object;
        this.resultCode = GTResultCode.OK;
        this.statusCode = 200;
        this.isSuccessful = true;
    }

    public GTResponse(T object, GTResultCode resultCode) {
        this.object = object;
        this.resultCode = resultCode;
        this.statusCode = resultCode.getStatusCode();
    }

    public GTResponse() {}

    public static <T> GTResponse<T> error(GTResultCode resultCode, String errorMessage,
                                          String apiEndpointUrl) {
        GTResponse<T> response = new GTResponse<T>();
        response.setResultCode(resultCode);
        response.setStatusCode(resultCode.getStatusCode());
        response.setApiEndpointUrl(apiEndpointUrl);
        response.setResultDetail(errorMessage);
        response.setIsSuccessful(false);
        return response;
    }

    public static <T> GTResponse<T> error(Integer statusCode, String errorMessage,
                                          String apiEndpointUrl) {
        GTResponse<T> response = new GTResponse<T>();
        response.setResultCode(GTResultCode.EMPTY);
        response.setStatusCode(statusCode);
        response.setApiEndpointUrl(apiEndpointUrl);
        response.setResultDetail(errorMessage);
        response.setIsSuccessful(false);
        return response;
    }

}
