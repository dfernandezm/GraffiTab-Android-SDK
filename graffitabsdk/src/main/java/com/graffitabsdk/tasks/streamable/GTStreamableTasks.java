package com.graffitabsdk.tasks.streamable;

import com.graffitabsdk.network.common.RequestPerformed;
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

    public RequestPerformed getFeed(GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(streamableService.getFeed(), GTListStreamablesResponse.class, responseHandler, true);
    }
}
