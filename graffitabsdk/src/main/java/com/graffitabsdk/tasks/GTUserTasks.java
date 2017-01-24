package com.graffitabsdk.tasks;

import com.graffitabsdk.config.cache.GTCacheService;
import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.user.UserService;
import com.graffitabsdk.network.service.user.response.GTListUsersResponse;
import com.graffitabsdk.network.service.user.response.GTUserResponse;

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

    public GTRequestPerformed getMe(boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return performJsonRequest(userService.getMe(), GTUserResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getMyFullProfile(boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return performJsonRequest(userService.getMyFullProfile(), GTUserResponse.class, responseHandler, useCache);
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
}
