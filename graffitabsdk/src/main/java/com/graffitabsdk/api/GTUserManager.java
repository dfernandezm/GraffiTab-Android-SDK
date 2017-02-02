package com.graffitabsdk.api;

import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.result.GTPasswordResetCompleteResult;
import com.graffitabsdk.network.common.result.GTRegistrationCompleteResult;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.user.response.GTListUsersResponse;
import com.graffitabsdk.network.service.user.response.GTUserResponse;
import com.graffitabsdk.tasks.GTUserTasks;
import com.graffitabsdk.tasks.login.GTLoginTasks;
import com.graffitabsdk.tasks.login.GTLogoutTask;

import javax.inject.Inject;

/**
 * Created by david on 09/11/2016.
 */

public class GTUserManager {

    private GTLoginTasks gtLoginTasks;
    private GTUserTasks gtUserTasks;
    private GTLogoutTask gtLogoutTask;

    @Inject
    public GTUserManager(GTUserTasks userTasks, GTLoginTasks loginTasks, GTLogoutTask gtLogoutTask) {
        this.gtUserTasks = userTasks;
        this.gtLoginTasks = loginTasks;
        this.gtLogoutTask = gtLogoutTask;
    }

    public GTRequestPerformed login(String username, String password, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtLoginTasks.login(username, password, responseHandler);
    }

    public GTRequestPerformed logout(GTResponseHandler<Void> responseHandler) {
        return gtLogoutTask.logout(responseHandler);
    }

    public GTRequestPerformed resetPassword(String email, GTResponseHandler<GTPasswordResetCompleteResult> responseHandler) {
        return gtUserTasks.resetPassword(email, responseHandler);
    }

    public GTRequestPerformed register(String firstName, String lastName, String email, String username, String password, GTResponseHandler<GTRegistrationCompleteResult> responseHandler) {
        return gtUserTasks.register(firstName, lastName, email, username, password, responseHandler);
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
        return gtUserTasks.getUserProfile(userId, useCache, responseHandler);
    }

    public GTRequestPerformed getFullUserProfile(int userId, boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtUserTasks.getFullUserProfile(userId, useCache, responseHandler);
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
