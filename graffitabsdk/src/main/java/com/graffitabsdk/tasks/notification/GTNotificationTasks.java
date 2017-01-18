package com.graffitabsdk.tasks.notification;

import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.notification.NotificationService;
import com.graffitabsdk.network.service.notification.response.GTListNotificationsResponse;
import com.graffitabsdk.tasks.cache.GTCacheService;
import com.graffitabsdk.tasks.common.GTNetworkTask;

import javax.inject.Inject;

/**
 * Created by georgichristov on 18/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTNotificationTasks extends GTNetworkTask {

    private NotificationService notificationService;

    @Inject
    public GTNotificationTasks(NotificationService notificationService, GTCacheService gtCacheService) {
        super.cacheService = gtCacheService;
        this.notificationService = notificationService;
    }

    public GTRequestPerformed getNotifications(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListNotificationsResponse> responseHandler) {
        return performJsonRequest(notificationService.getNotifications(parameters.getParameters()), GTListNotificationsResponse.class, responseHandler, useCache);
    }
}
