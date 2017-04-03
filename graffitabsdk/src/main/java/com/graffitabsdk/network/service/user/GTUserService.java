package com.graffitabsdk.network.service.user;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.model.GTExternalProvider;
import com.graffitabsdk.model.GTUserSocialFriendsContainer;
import com.graffitabsdk.network.common.result.GTActionCompleteResult;
import com.graffitabsdk.network.service.assets.response.GTAssetResponse;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.user.response.GTListActivityContainersResponse;
import com.graffitabsdk.network.service.user.response.GTListUsersResponse;
import com.graffitabsdk.network.service.user.response.GTUserResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.Map;

/**
 * Created by david on 10/11/2016.
 */
public interface GTUserService {

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.USER_LOGIN_ENDPOINT)
    Call<GTUserResponse> login(@Body GTLoginData loginData);

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.USER_LOGIN_WITH_EXTERNAL_PROVIDER_ENDPOINT)
    Call<GTUserResponse> loginExternal(@Body GTLoginExternalProviderData loginExternalProviderData);

    @GET(GTApiConstants.USER_LOGOUT_ENDPOINT)
    Call<Void> logout();

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.USER_RESET_PASSWORD)
    Call<GTActionCompleteResult> resetPassword(@Body GTResetPasswordData resetPasswordData);

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.USERS_ENDPOINT)
    Call<GTActionCompleteResult> register(@Body GTRegisterData registerData);

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.USER_EXTERNAL_PROVIDERS_ENDPOINT)
    Call<GTActionCompleteResult> register(@Body GTRegisterExternalProviderData registerExternalProviderData);

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.MY_EXTERNAL_PROVIDERS_ENDPOINT)
    Call<GTUserResponse> linkExternalProvider(@Body GTExternalProviderData externalProviderData);

    @Headers("Content-Type: application/json")
    @HTTP(method = "DELETE", path = GTApiConstants.MY_EXTERNAL_PROVIDERS_ENDPOINT, hasBody = true)
    Call<GTUserResponse> unlinkExternalProvider(@Body GTExternalProviderMetadata externalProviderMetadata);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.ME_ENDPOINT)
    Call<GTUserResponse> getMe();

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.MY_FULL_PROFILE_ENDPOINT)
    Call<GTUserResponse> getMyFullProfile();

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.USER_PROFILE_ENDPOINT)
    Call<GTUserResponse> getUserProfile(@Path("userId") int userId);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.USER_FULL_PROFILE_ENDPOINT)
    Call<GTUserResponse> getFullUserProfile(@Path("userId") int userId);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.USER_FULL_PROFILE_BY_USERNAME_ENDPOINT)
    Call<GTUserResponse> getFullUserProfileForUsername(@Path("username") String username);

    @Headers("Content-Type: application/json")
    @PUT(GTApiConstants.ME_ENDPOINT)
    Call<GTUserResponse> edit(@Body GTEditProfileData profileData);

    @Headers("Content-Type: application/json")
    @PUT(GTApiConstants.ME_CHANGE_PASSWORD_ENDPOINT)
    Call<GTActionCompleteResult> editPassword(@Body GTEditPasswordData passwordData);

    @Multipart
    @POST(GTApiConstants.MY_AVATAR)
    Call<GTAssetResponse> editAvatar(@Part MultipartBody.Part file, @Part("name") RequestBody name);

    @Headers("Content-Type: application/json")
    @PUT(GTApiConstants.ME_IMPORT_AVATAR_ENDPOINT)
    Call<GTAssetResponse> importAvatar(@Path("socialType") GTExternalProvider.GTExternalProviderType type);

    @Headers("Content-Type: application/json")
    @DELETE(GTApiConstants.MY_AVATAR)
    Call<GTActionCompleteResult> deleteAvatar();

    @Multipart
    @POST(GTApiConstants.MY_COVER)
    Call<GTAssetResponse> editCover(@Part MultipartBody.Part file, @Part("name") RequestBody name);

    @Headers("Content-Type: application/json")
    @DELETE(GTApiConstants.MY_COVER)
    Call<GTActionCompleteResult> deleteCover();

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.USERS_MOST_ACTIVE_ENDPOINT)
    Call<GTListUsersResponse> getMostActiveUsers(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.USER_LIKES_ENDPOINT)
    Call<GTListStreamablesResponse> getLikes(@Path("userId") int userId, @QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.USER_STREAMABLES_ENDPOINT)
    Call<GTListStreamablesResponse> getPosts(@Path("userId") int userId, @QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.USER_MENTIONS_ENDPOINT)
    Call<GTListStreamablesResponse> getMentions(@Path("userId") int userId, @QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.MY_PRIVATE_ENDPOINT)
    Call<GTListStreamablesResponse> getPrivatePosts(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.USER_FOLLOWERS_ENDPOINT)
    Call<GTListUsersResponse> getFollowers(@Path("userId") int userId, @QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.USER_FOLLOWING_ENDPOINT)
    Call<GTListUsersResponse> getFollowing(@Path("userId") int userId, @QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.USER_FOLLOWERS_ENDPOINT)
    Call<GTUserResponse> follow(@Path("userId") int userId);

    @Headers("Content-Type: application/json")
    @DELETE(GTApiConstants.USER_FOLLOWERS_ENDPOINT)
    Call<GTUserResponse> unfollow(@Path("userId") int userId);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.USERS_SEARCH_ENDPOINT)
    Call<GTListUsersResponse> search(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.MY_FOLLOWERS_ACTIVITY_ENDPOINT)
    Call<GTListActivityContainersResponse> getFollowersActivity(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.ME_FILTER_SOCIAL_FRIENDS_ENDPOINT)
    Call<GTUserSocialFriendsContainer> findSocialFriends(@Path("socialType") String socialType, @QueryMap Map<String, String> parameters);

}

