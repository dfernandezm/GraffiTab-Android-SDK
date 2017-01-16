package com.graffitabsdk.tasks.user.login;

import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.network.service.user.ResetPasswordData;
import com.graffitabsdk.network.service.user.UserService;
import com.graffitabsdk.tasks.cache.GTCacheService;
import com.graffitabsdk.tasks.common.GTNetworkTask;

import javax.inject.Inject;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTResetPasswordTask extends GTNetworkTask<String> {

    private UserService userService;

    @Inject
    public GTResetPasswordTask(UserService userService, GTCacheService cacheService) {
        this.userService = userService;
        this.cacheService = cacheService;
    }

    public RequestPerformed<String> resetPassword(String email, GTResponseHandler<String> responseHandler) {
        ResetPasswordData resetPasswordData = new ResetPasswordData(email);
        return performJsonRequest(userService.resetPassword(resetPasswordData), String.class, null, responseHandler, false);
    }
}
