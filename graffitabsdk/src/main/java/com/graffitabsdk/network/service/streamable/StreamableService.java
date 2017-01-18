package com.graffitabsdk.network.service.streamable;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public interface StreamableService {

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.MY_FEED_ENDPOINT)
    Call<GTListStreamablesResponse> getFeed(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.STREAMABLES_POPULAR_ENDPOINT)
    Call<GTListStreamablesResponse> getPopular(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.STREAMABLES_NEWEST_ENDPOINT)
    Call<GTListStreamablesResponse> getNewest(@QueryMap Map<String, String> parameters);
}
