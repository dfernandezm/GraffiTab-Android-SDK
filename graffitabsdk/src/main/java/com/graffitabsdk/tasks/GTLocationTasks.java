package com.graffitabsdk.tasks;

import com.graffitabsdk.config.cache.GTCacheService;
import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.result.GTLocationDeletedResult;
import com.graffitabsdk.network.service.location.LocationService;
import com.graffitabsdk.network.service.location.data.edit.EditLocationData;
import com.graffitabsdk.network.service.location.data.edit.EditLocationMetadata;
import com.graffitabsdk.network.service.location.response.GTListLocationsResponse;
import com.graffitabsdk.network.service.location.response.GTLocationResponse;

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

    public GTRequestPerformed createLocation(String address, double latitude, double longitude, GTResponseHandler<GTLocationResponse> responseHandler) {
        EditLocationMetadata editLocationMetadata = new EditLocationMetadata(address, latitude, longitude);
        EditLocationData editLocationData = new EditLocationData(editLocationMetadata);
        return performJsonRequest(locationService.createLocation(editLocationData), GTLocationResponse.class, responseHandler);
    }

    public GTRequestPerformed editLocation(int locationId, String address, double latitude, double longitude, GTResponseHandler<GTLocationResponse> responseHandler) {
        EditLocationMetadata editLocationMetadata = new EditLocationMetadata(address, latitude, longitude);
        EditLocationData editLocationData = new EditLocationData(editLocationMetadata);
        return performJsonRequest(locationService.editLocation(locationId, editLocationData), GTLocationResponse.class, responseHandler);
    }

    public GTRequestPerformed deleteLocation(int locationId, GTResponseHandler<GTLocationDeletedResult> responseHandler) {
        return performJsonRequest(locationService.deleteLocation(locationId), GTLocationDeletedResult.class, responseHandler);
    }
}
