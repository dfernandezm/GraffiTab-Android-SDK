package com.graffitabsdk.network.service.user;

import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.result.GTActionCompleteResult;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.user.response.GTListUsersResponse;
import com.graffitabsdk.network.service.user.response.GTUserResponse;
import com.graffitabsdk.sdk.GTSDK;
import com.graffitabsdk.sdk.cache.GTCacheService;
import com.graffitabsdk.sdk.events.users.GTUserFollowedEvent;
import com.graffitabsdk.sdk.events.users.GTUserUnfollowedEvent;

import javax.inject.Inject;

/**
 * Created by david on 01/12/2016.
 */

public class GTUserTasks extends GTNetworkTask {

    private UserService userService;

    @Inject
    public GTUserTasks(UserService userService, GTCacheService gtCacheService) {
        super.cacheService = gtCacheService;
        this.userService = userService;
    }

    public GTRequestPerformed register(String firstName, String lastName, String email, String username, String password, GTResponseHandler<GTActionCompleteResult> responseHandler) {
        RegisterMetadata registerUserData = new RegisterMetadata(firstName, lastName, email, username, password);
        RegisterData registerData = new RegisterData(registerUserData);
        return performJsonRequest(userService.register(registerData), GTActionCompleteResult.class, responseHandler);
    }

    public GTRequestPerformed resetPassword(String email, GTResponseHandler<GTActionCompleteResult> responseHandler) {
        ResetPasswordData resetPasswordData = new ResetPasswordData(email);
        return performJsonRequest(userService.resetPassword(resetPasswordData), GTActionCompleteResult.class, responseHandler);
    }

    public GTRequestPerformed editPassword(String currentPassword, String newPassword, GTResponseHandler<GTActionCompleteResult> responseHandler) {
        EditPasswordData editPasswordData = new EditPasswordData(currentPassword, newPassword);
        return performJsonRequest(userService.editPassword(editPasswordData), GTActionCompleteResult.class, responseHandler);
    }

    public GTRequestPerformed getMostActive(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListUsersResponse> responseHandler) {
        return performJsonRequest(userService.getMostActiveUsers(parameters.getParameters()), GTListUsersResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getLikes(int userId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(userService.getLikes(userId, parameters.getParameters()), GTListStreamablesResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getPosts(int userId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(userService.getPosts(userId, parameters.getParameters()), GTListStreamablesResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getMentions(int userId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(userService.getMentions(userId, parameters.getParameters()), GTListStreamablesResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getPrivatePosts(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(userService.getPrivatePosts(parameters.getParameters()), GTListStreamablesResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getFollowers(int userId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListUsersResponse> responseHandler) {
        return performJsonRequest(userService.getFollowers(userId, parameters.getParameters()), GTListUsersResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getFollowing(int userId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListUsersResponse> responseHandler) {
        return performJsonRequest(userService.getFollowing(userId, parameters.getParameters()), GTListUsersResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed follow(int userId, final GTResponseHandler<GTUserResponse> responseHandler) {
        // Update local logged in user.
        GTUser me = GTSDK.getAccountManager().getLoggedInUser();
        me.addToFollowingCount();

        return performJsonRequest(userService.follow(userId), GTUserResponse.class, new GTResponseHandler<GTUserResponse>() {

            @Override
            public void onSuccess(GTResponse<GTUserResponse> gtResponse) {
                GTSDK.postEvent(new GTUserFollowedEvent(gtResponse.getObject().user));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTUserResponse> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }

    public GTRequestPerformed unfollow(int userId, final GTResponseHandler<GTUserResponse> responseHandler) {
        // Update local logged in user.
        GTUser me = GTSDK.getAccountManager().getLoggedInUser();
        me.removeFromFollowingCount();

        return performJsonRequest(userService.unfollow(userId), GTUserResponse.class, new GTResponseHandler<GTUserResponse>() {

            @Override
            public void onSuccess(GTResponse<GTUserResponse> gtResponse) {
                GTSDK.postEvent(new GTUserUnfollowedEvent(gtResponse.getObject().user));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTUserResponse> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }

    public GTRequestPerformed search(GTQueryParameters parameters, GTResponseHandler<GTListUsersResponse> responseHandler) {
        return performJsonRequest(userService.search(parameters.getParameters()), GTListUsersResponse.class, responseHandler);
    }
}
