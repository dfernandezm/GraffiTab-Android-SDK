package com.graffitabsdk.network.service.location;

import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.result.GTActionCompleteResult;
import com.graffitabsdk.network.service.location.response.GTListLocationsResponse;
import com.graffitabsdk.network.service.location.response.GTLocationResponse;
import com.graffitabsdk.sdk.GTSDK;
import com.graffitabsdk.sdk.cache.GTCacheService;
import com.graffitabsdk.sdk.events.locations.GTLocationCreatedEvent;
import com.graffitabsdk.sdk.events.locations.GTLocationDeletedEvent;
import com.graffitabsdk.sdk.events.locations.GTLocationUpdatedEvent;

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

    public GTRequestPerformed createLocation(String address, double latitude, double longitude, final GTResponseHandler<GTLocationResponse> responseHandler) {
        EditLocationMetadata editLocationMetadata = new EditLocationMetadata(address, latitude, longitude);
        EditLocationData editLocationData = new EditLocationData(editLocationMetadata);
        return performJsonRequest(locationService.createLocation(editLocationData), GTLocationResponse.class, new GTResponseHandler<GTLocationResponse>() {

            @Override
            public void onSuccess(GTResponse<GTLocationResponse> gtResponse) {
                GTSDK.postEvent(new GTLocationCreatedEvent(gtResponse.getObject().location));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTLocationResponse> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }

    public GTRequestPerformed editLocation(int locationId, String address, double latitude, double longitude, final GTResponseHandler<GTLocationResponse> responseHandler) {
        EditLocationMetadata editLocationMetadata = new EditLocationMetadata(address, latitude, longitude);
        EditLocationData editLocationData = new EditLocationData(editLocationMetadata);
        return performJsonRequest(locationService.editLocation(locationId, editLocationData), GTLocationResponse.class, new GTResponseHandler<GTLocationResponse>() {

            @Override
            public void onSuccess(GTResponse<GTLocationResponse> gtResponse) {
                GTSDK.postEvent(new GTLocationUpdatedEvent(gtResponse.getObject().location));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTLocationResponse> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }

    public GTRequestPerformed deleteLocation(final int locationId, final GTResponseHandler<GTActionCompleteResult> responseHandler) {
        return performJsonRequest(locationService.deleteLocation(locationId), GTActionCompleteResult.class, new GTResponseHandler<GTActionCompleteResult>() {

            @Override
            public void onSuccess(GTResponse<GTActionCompleteResult> gtResponse) {
                GTSDK.postEvent(new GTLocationDeletedEvent(locationId));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTActionCompleteResult> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }
}
