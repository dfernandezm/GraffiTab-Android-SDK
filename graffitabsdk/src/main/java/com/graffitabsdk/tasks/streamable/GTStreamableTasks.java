package com.graffitabsdk.tasks.streamable;

import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.streamable.StreamableService;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.tasks.cache.GTCacheService;
import com.graffitabsdk.tasks.common.GTNetworkTask;

import javax.inject.Inject;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTStreamableTasks extends GTNetworkTask {

    private StreamableService streamableService;

    @Inject
    public GTStreamableTasks(StreamableService streamableService, GTCacheService gtCacheService) {
        super.cacheService = gtCacheService;
        this.streamableService = streamableService;
    }

    public GTRequestPerformed getFeed(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(streamableService.getFeed(parameters.getParameters()), GTListStreamablesResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getPopular(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(streamableService.getPopular(parameters.getParameters()), GTListStreamablesResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getNewest(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(streamableService.getNewest(parameters.getParameters()), GTListStreamablesResponse.class, responseHandler, useCache);
    }
}
