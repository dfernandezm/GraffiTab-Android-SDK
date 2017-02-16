package com.graffitabsdk.network.service.device;

import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.result.GTActionCompleteResult;
import com.graffitabsdk.sdk.cache.GTCacheService;

import javax.inject.Inject;

/**
 * Created by georgichristov on 02/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTDeviceTasks extends GTNetworkTask {

    private DeviceService deviceService;

    @Inject
    public GTDeviceTasks(DeviceService deviceService, GTCacheService gtCacheService) {
        super.cacheService = gtCacheService;
        this.deviceService = deviceService;
    }

    public GTRequestPerformed linkDevice(String token, GTResponseHandler<GTActionCompleteResult> responseHandler) {
        LinkDeviceMetadata linkDeviceMetadata = new LinkDeviceMetadata(token);
        LinkDeviceData linkDeviceData = new LinkDeviceData(linkDeviceMetadata);
        return performJsonRequest(deviceService.linkDevice(linkDeviceData), GTActionCompleteResult.class, responseHandler);
    }

    public GTRequestPerformed unlinkDevice(String token, GTResponseHandler<GTActionCompleteResult> responseHandler) {
        LinkDeviceMetadata linkDeviceMetadata = new LinkDeviceMetadata(token);
        LinkDeviceData linkDeviceData = new LinkDeviceData(linkDeviceMetadata);
        return performJsonRequest(deviceService.unlinkDevice(linkDeviceData), GTActionCompleteResult.class, responseHandler);
    }
}
