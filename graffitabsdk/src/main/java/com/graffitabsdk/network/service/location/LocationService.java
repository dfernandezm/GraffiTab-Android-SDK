package com.graffitabsdk.network.service.location;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.network.service.location.response.GTListLocationsResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
}
