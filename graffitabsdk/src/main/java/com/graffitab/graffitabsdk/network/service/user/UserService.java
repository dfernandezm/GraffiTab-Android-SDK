package com.graffitab.graffitabsdk.network.service.user;

import com.graffitab.graffitabsdk.constants.GTApiConstants;
import com.graffitab.graffitabsdk.model.GTUser;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by david on 10/11/2016.
 */
public interface UserService {

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.LOGIN_ENDPOINT)
    Call<Map<String,GTUser>> login(@Body LoginData loginData);
}

