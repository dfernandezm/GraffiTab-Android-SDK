package com.graffitabsdk.tasks;

import com.graffitabsdk.config.cache.GTCacheService;
import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.result.GTDeviceLinkResult;
import com.graffitabsdk.network.service.device.DeviceService;
import com.graffitabsdk.network.service.device.data.LinkDeviceData;
import com.graffitabsdk.network.service.device.data.LinkDeviceMetadata;

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

    public GTRequestPerformed linkDevice(String token, GTResponseHandler<GTDeviceLinkResult> responseHandler) {
        LinkDeviceMetadata linkDeviceMetadata = new LinkDeviceMetadata(token);
        LinkDeviceData linkDeviceData = new LinkDeviceData(linkDeviceMetadata);
        return performJsonRequest(deviceService.linkDevice(linkDeviceData), GTDeviceLinkResult.class, responseHandler);
    }

    public GTRequestPerformed unlinkDevice(String token, GTResponseHandler<GTDeviceLinkResult> responseHandler) {
        LinkDeviceMetadata linkDeviceMetadata = new LinkDeviceMetadata(token);
        LinkDeviceData linkDeviceData = new LinkDeviceData(linkDeviceMetadata);
        return performJsonRequest(deviceService.unlinkDevice(linkDeviceData), GTDeviceLinkResult.class, responseHandler);
    }
}
