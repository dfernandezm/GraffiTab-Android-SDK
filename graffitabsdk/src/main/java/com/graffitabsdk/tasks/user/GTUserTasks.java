package com.graffitabsdk.tasks.user;

import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.user.UserService;
import com.graffitabsdk.network.service.user.response.GTUserResponse;
import com.graffitabsdk.tasks.cache.GTCacheService;
import com.graffitabsdk.tasks.common.GTNetworkTask;

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

    public RequestPerformed getMe(GTResponseHandler<GTUserResponse> responseHandler) {
        return performJsonRequest(userService.getMe(), GTUserResponse.class, responseHandler, true);
    }

    public RequestPerformed getMyFullProfile(GTResponseHandler<GTUserResponse> responseHandler) {
        return performJsonRequest(userService.getMyFullProfile(), GTUserResponse.class, responseHandler, true);
    }
}
