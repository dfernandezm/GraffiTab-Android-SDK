package com.graffitabsdk.network.service.streamable;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public interface StreamableService {

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.MY_FEED_ENDPOINT)
    Call<GTListStreamablesResponse> getFeed();
}
