package com.graffitabsdk.network.service.device;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.network.common.result.GTActionCompleteResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by georgichristov on 02/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public interface GTDeviceService {

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.MY_DEVICES_ENDPOINT)
    Call<GTActionCompleteResult> linkDevice(@Body GTLinkDeviceData linkDeviceData);

    @Headers("Content-Type: application/json")
    @HTTP(method = "DELETE", path = GTApiConstants.MY_DEVICES_ENDPOINT, hasBody = true)
    Call<GTActionCompleteResult> unlinkDevice(@Body GTLinkDeviceData linkDeviceData);
}
