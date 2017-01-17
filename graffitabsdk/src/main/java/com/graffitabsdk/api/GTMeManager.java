package com.graffitabsdk.api;

import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.user.response.GTUserResponse;
import com.graffitabsdk.tasks.streamable.GTStreamableTasks;
import com.graffitabsdk.tasks.user.GTUserTasks;

import javax.inject.Inject;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTMeManager {

    private GTUserTasks gtUserTasks;
    private GTStreamableTasks gtStreamableTasks;

    @Inject
    public GTMeManager(GTUserTasks userTasks, GTStreamableTasks gtStreamableTasks) {
        this.gtUserTasks = userTasks;
        this.gtStreamableTasks = gtStreamableTasks;
    }

    public RequestPerformed getMe(GTResponseHandler<GTUserResponse> responseHandler) {
        return gtUserTasks.getMe(responseHandler);
    }

    public RequestPerformed getMyFullProfile(GTResponseHandler<GTUserResponse> responseHandler) {
        return gtUserTasks.getMyFullProfile(responseHandler);
    }

    public RequestPerformed getFeed(GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return gtStreamableTasks.getFeed(responseHandler);
    }
}
