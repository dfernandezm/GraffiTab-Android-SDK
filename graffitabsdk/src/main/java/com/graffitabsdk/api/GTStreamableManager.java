package com.graffitabsdk.api;

import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.tasks.streamable.GTStreamableTasks;

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
}
