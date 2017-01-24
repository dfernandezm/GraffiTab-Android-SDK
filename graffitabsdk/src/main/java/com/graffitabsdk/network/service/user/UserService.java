package com.graffitabsdk.network.service.user;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.network.common.result.GTPasswordResetCompleteResult;
import com.graffitabsdk.network.common.result.GTRegistrationCompleteResult;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.user.data.LoginData;
import com.graffitabsdk.network.service.user.data.ResetPasswordData;
import com.graffitabsdk.network.service.user.data.register.RegisterData;
import com.graffitabsdk.network.service.user.response.GTListUsersResponse;
import com.graffitabsdk.network.service.user.response.GTUserResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

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

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.USERS_MOST_ACTIVE_ENDPOINT)
    Call<GTListUsersResponse> getMostActiveUsers(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.USER_LIKES_ENDPOINT)
    Call<GTListStreamablesResponse> getLikes(@Path("userId") int userId, @QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.USER_STREAMABLES_ENDPOINT)
    Call<GTListStreamablesResponse> getPosts(@Path("userId") int userId, @QueryMap Map<String, String> parameters);
}

