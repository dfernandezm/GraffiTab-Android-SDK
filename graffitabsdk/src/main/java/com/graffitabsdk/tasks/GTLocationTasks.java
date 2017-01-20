package com.graffitabsdk.tasks;

import com.graffitabsdk.config.cache.GTCacheService;
import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.location.LocationService;
import com.graffitabsdk.network.service.location.response.GTListLocationsResponse;

import javax.inject.Inject;

/**
 * Created by georgichristov on 20/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTLocationTasks extends GTNetworkTask {

    private LocationService locationService;

    @Inject
    public GTLocationTasks(LocationService locationService, GTCacheService gtCacheService) {
        super.cacheService = gtCacheService;
        this.locationService = locationService;
    }

    public GTRequestPerformed getLocations(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListLocationsResponse> responseHandler) {
        return performJsonRequest(locationService.getLocations(parameters.getParameters()), GTListLocationsResponse.class, responseHandler, useCache);
    }
}
