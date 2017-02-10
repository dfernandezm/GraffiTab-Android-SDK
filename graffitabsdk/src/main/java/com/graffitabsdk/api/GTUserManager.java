package com.graffitabsdk.api;

import com.graffitabsdk.model.GTExternalProvider;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.result.GTActionCompleteResult;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.user.GTLoginTasks;
import com.graffitabsdk.network.service.user.GTLogoutTask;
import com.graffitabsdk.network.service.user.GTUserProfileTasks;
import com.graffitabsdk.network.service.user.GTUserTasks;
import com.graffitabsdk.network.service.user.response.GTListUsersResponse;
import com.graffitabsdk.network.service.user.response.GTUserResponse;

import javax.inject.Inject;


/**
 * Created by david on 09/11/2016.
 */

public class GTUserManager {

    private GTLoginTasks gtLoginTasks;
    private GTUserTasks gtUserTasks;
    private GTLogoutTask gtLogoutTask;
    private GTUserProfileTasks gtUserProfileTasks;

    @Inject
    public GTUserManager(GTUserTasks userTasks, GTLoginTasks loginTasks, GTLogoutTask gtLogoutTask,
                         GTUserProfileTasks gtUserProfileTasks) {
        this.gtUserTasks = userTasks;
        this.gtLoginTasks = loginTasks;
        this.gtLogoutTask = gtLogoutTask;
        this.gtUserProfileTasks = gtUserProfileTasks;
    }

    public GTRequestPerformed login(String username, String password, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtLoginTasks.login(username, password, responseHandler);
    }

    public GTRequestPerformed login(String externalId, String externalToken, GTExternalProvider.GTExternalProviderType type, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtLoginTasks.login(externalId, externalToken, type, responseHandler);
    }

    public GTRequestPerformed logout(GTResponseHandler<Void> responseHandler) {
        return gtLogoutTask.logout(responseHandler);
    }

    public GTRequestPerformed resetPassword(String email, GTResponseHandler<GTActionCompleteResult> responseHandler) {
        return gtUserTasks.resetPassword(email, responseHandler);
    }

    public GTRequestPerformed register(String firstName, String lastName, String email, String username, String password, GTResponseHandler<GTActionCompleteResult> responseHandler) {
        return gtUserTasks.register(firstName, lastName, email, username, password, responseHandler);
    }

    public GTRequestPerformed register(GTExternalProvider.GTExternalProviderType type, String externalId, String token, String firstName, String lastName, String email, String username, GTResponseHandler<GTActionCompleteResult> responseHandler) {
        return gtUserTasks.register(type, externalId, token, firstName, lastName, email, username, responseHandler);
    }

    public GTRequestPerformed getMostActiveUsers(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListUsersResponse> responseHandler) {
        return gtUserTasks.getMostActive(useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getLikes(int userId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return gtUserTasks.getLikes(userId, useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getPosts(int userId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return gtUserTasks.getPosts(userId, useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getMentions(int userId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return gtUserTasks.getMentions(userId, useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getFollowers(int userId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListUsersResponse> responseHandler) {
        return gtUserTasks.getFollowers(userId, useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getFollowing(int userId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListUsersResponse> responseHandler) {
        return gtUserTasks.getFollowing(userId, useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getUserProfile(int userId, boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtUserProfileTasks.getUserProfile(userId, useCache, responseHandler);
    }

    public GTRequestPerformed getFullUserProfile(int userId, boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtUserProfileTasks.getFullUserProfile(userId, useCache, responseHandler);
    }

    public GTRequestPerformed getFullUserProfileForUsername(String username, boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtUserProfileTasks.getFullUserProfileForUsername(username, useCache, responseHandler);
    }

    public GTRequestPerformed follow(int userId, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtUserTasks.follow(userId, responseHandler);
    }

    public GTRequestPerformed unfollow(int userId, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtUserTasks.unfollow(userId, responseHandler);
    }

    public GTRequestPerformed search(GTQueryParameters parameters, GTResponseHandler<GTListUsersResponse> responseHandler) {
        return gtUserTasks.search(parameters, responseHandler);
    }
}
