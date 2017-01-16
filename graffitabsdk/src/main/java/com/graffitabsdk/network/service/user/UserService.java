package com.graffitabsdk.network.service.user;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.service.user.data.LoginData;
import com.graffitabsdk.network.service.user.data.ResetPasswordData;
import com.graffitabsdk.network.service.user.data.register.RegisterData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by david on 10/11/2016.
 */
public interface UserService {

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.USER_LOGIN_ENDPOINT)
    Call<Map<String,GTUser>> login(@Body LoginData loginData);

    @GET(GTApiConstants.USER_LOGOUT_ENDPOINT)
    Call<Void> logout();

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.USER_RESET_PASSWORD)
    Call<Map<String,String>> resetPassword(@Body ResetPasswordData resetPasswordData);

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.USERS_ENDPOINT)
    Call<Map<String,String>> register(@Body RegisterData registerData);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.ME_ENDPOINT)
    Call<Map<String,GTUser>> getMe();

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.MY_FULL_PROFILE_ENDPOINT)
    Call<Map<String,GTUser>> getMyFullProfile();
}

