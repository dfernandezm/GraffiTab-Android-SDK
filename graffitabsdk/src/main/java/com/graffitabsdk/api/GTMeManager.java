package com.graffitabsdk.api;

import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.location.response.GTListLocationsResponse;
import com.graffitabsdk.network.service.notification.response.GTListNotificationsResponse;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.user.response.GTUserResponse;
import com.graffitabsdk.tasks.GTLocationTasks;
import com.graffitabsdk.tasks.GTNotificationTasks;
import com.graffitabsdk.tasks.GTStreamableTasks;
import com.graffitabsdk.tasks.GTUserTasks;

import javax.inject.Inject;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTMeManager {

    private GTUserTasks gtUserTasks;
    private GTStreamableTasks gtStreamableTasks;
    private GTNotificationTasks gtNotificationTasks;
    private GTLocationTasks gtLocationTasks;

    @Inject
    public GTMeManager(GTUserTasks userTasks, GTStreamableTasks gtStreamableTasks, GTNotificationTasks gtNotificationTasks, GTLocationTasks gtLocationTasks) {
        this.gtUserTasks = userTasks;
        this.gtStreamableTasks = gtStreamableTasks;
        this.gtNotificationTasks = gtNotificationTasks;
        this.gtLocationTasks = gtLocationTasks;
    }

    public GTRequestPerformed getMe(boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtUserTasks.getMe(useCache, responseHandler);
    }

    public GTRequestPerformed getMyFullProfile(boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtUserTasks.getMyFullProfile(useCache, responseHandler);
    }

    public GTRequestPerformed getFeed(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return gtStreamableTasks.getFeed(useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getNotifications(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListNotificationsResponse> responseHandler) {
        return gtNotificationTasks.getNotifications(useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getLocations(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListLocationsResponse> responseHandler) {
        return gtLocationTasks.getLocations(useCache, parameters, responseHandler);
    }
}
