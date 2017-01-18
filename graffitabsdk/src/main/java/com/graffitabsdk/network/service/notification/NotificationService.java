package com.graffitabsdk.network.service.notification;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.network.service.notification.response.GTListNotificationsResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * Created by georgichristov on 18/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public interface NotificationService {

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.MY_NOTIFICATIONS_ENDPOINT)
    Call<GTListNotificationsResponse> getNotifications(@QueryMap Map<String, String> parameters);
}
