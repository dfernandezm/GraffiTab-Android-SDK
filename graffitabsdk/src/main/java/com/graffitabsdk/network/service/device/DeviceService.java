package com.graffitabsdk.network.service.device;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.network.common.result.GTDeviceLinkResult;
import com.graffitabsdk.network.service.device.data.LinkDeviceData;

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
public interface DeviceService {

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.MY_DEVICES_ENDPOINT)
    Call<GTDeviceLinkResult> linkDevice(@Body LinkDeviceData linkDeviceData);

    @Headers("Content-Type: application/json")
    @HTTP(method = "DELETE", path = GTApiConstants.MY_DEVICES_ENDPOINT, hasBody = true)
    Call<GTDeviceLinkResult> unlinkDevice(@Body LinkDeviceData linkDeviceData);
}
