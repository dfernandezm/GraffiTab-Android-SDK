package com.graffitabsdk.network.service.user;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.network.common.result.GTPasswordResetCompleteResult;
import com.graffitabsdk.network.common.result.GTRegistrationCompleteResult;
import com.graffitabsdk.network.service.user.data.LoginData;
import com.graffitabsdk.network.service.user.data.ResetPasswordData;
import com.graffitabsdk.network.service.user.data.register.RegisterData;
import com.graffitabsdk.network.service.user.response.GTUserResponse;

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
    Call<GTUserResponse> login(@Body LoginData loginData);

    @GET(GTApiConstants.USER_LOGOUT_ENDPOINT)
    Call<Void> logout();

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.USER_RESET_PASSWORD)
    Call<GTPasswordResetCompleteResult> resetPassword(@Body ResetPasswordData resetPasswordData);

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.USERS_ENDPOINT)
    Call<GTRegistrationCompleteResult> register(@Body RegisterData registerData);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.ME_ENDPOINT)
    Call<GTUserResponse> getMe();

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.MY_FULL_PROFILE_ENDPOINT)
    Call<GTUserResponse> getMyFullProfile();
}

