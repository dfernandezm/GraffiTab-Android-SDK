package com.graffitabsdk.api;

import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.streamable.response.GTListCommentsResponse;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.streamable.response.GTStreamableResponse;
import com.graffitabsdk.network.service.user.response.GTListUsersResponse;
import com.graffitabsdk.tasks.GTStreamableTasks;

import javax.inject.Inject;

/**
 * Created by georgichristov on 17/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTStreamableManager {

    private GTStreamableTasks gtStreamableTasks;

    @Inject
    public GTStreamableManager(GTStreamableTasks gtStreamableTasks) {
        this.gtStreamableTasks = gtStreamableTasks;
    }

    public GTRequestPerformed getPopular(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return gtStreamableTasks.getPopular(useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getNewest(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return gtStreamableTasks.getNewest(useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getStreamable(int streamableId, boolean useCache, GTResponseHandler<GTStreamableResponse> responseHandler) {
        return gtStreamableTasks.getStreamable(streamableId, useCache, responseHandler);
    }

    public GTRequestPerformed getLikers(int streamableId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListUsersResponse> responseHandler) {
        return gtStreamableTasks.getLikers(streamableId, useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getComments(int streamableId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListCommentsResponse> responseHandler) {
        return gtStreamableTasks.getComments(streamableId, useCache, parameters, responseHandler);
    }
}
