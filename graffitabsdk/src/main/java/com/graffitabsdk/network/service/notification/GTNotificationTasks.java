package com.graffitabsdk.network.service.notification;

import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.notification.response.GTListNotificationsResponse;
import com.graffitabsdk.network.service.user.response.GTUnseenNotificationsResponse;
import com.graffitabsdk.sdk.cache.GTCacheService;

import javax.inject.Inject;

/**
 * Created by georgichristov on 18/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTNotificationTasks extends GTNetworkTask {

    private GTNotificationService notificationService;

    @Inject
    public GTNotificationTasks(GTNotificationService notificationService, GTCacheService gtCacheService) {
        super.cacheService = gtCacheService;
        this.notificationService = notificationService;
    }

    public GTRequestPerformed getNotifications(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListNotificationsResponse> responseHandler) {
        return performJsonRequest(notificationService.getNotifications(parameters.getParameters()), GTListNotificationsResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getUnseenNotifications(GTResponseHandler<GTUnseenNotificationsResponse> responseHandler) {
        return performJsonRequest(notificationService.getUnseenNotifications(), GTUnseenNotificationsResponse.class, responseHandler);
    }
}
