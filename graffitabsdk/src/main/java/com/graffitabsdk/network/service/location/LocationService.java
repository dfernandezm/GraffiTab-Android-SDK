package com.graffitabsdk.network.service.location;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.network.common.result.GTActionCompleteResult;
import com.graffitabsdk.network.service.location.response.GTListLocationsResponse;
import com.graffitabsdk.network.service.location.response.GTLocationResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by georgichristov on 20/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public interface LocationService {

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.MY_LOCATIONS_ENDPOINT)
    Call<GTListLocationsResponse> getLocations(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.MY_LOCATIONS_ENDPOINT)
    Call<GTLocationResponse> createLocation(@Body EditLocationData editLocationData);

    @Headers("Content-Type: application/json")
    @PUT(GTApiConstants.MY_LOCATION_ENDPOINT)
    Call<GTLocationResponse> editLocation(@Path("locationId") int locationId, @Body EditLocationData editLocationData);

    @Headers("Content-Type: application/json")
    @DELETE(GTApiConstants.MY_LOCATION_ENDPOINT)
    Call<GTActionCompleteResult> deleteLocation(@Path("locationId") int locationId);
}
