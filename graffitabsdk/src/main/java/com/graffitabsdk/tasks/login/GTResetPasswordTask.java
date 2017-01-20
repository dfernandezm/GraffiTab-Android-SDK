package com.graffitabsdk.tasks.login;

import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.result.GTPasswordResetCompleteResult;
import com.graffitabsdk.network.service.user.UserService;
import com.graffitabsdk.network.service.user.data.ResetPasswordData;
import com.graffitabsdk.config.cache.GTCacheService;
import com.graffitabsdk.network.call.GTNetworkTask;

import javax.inject.Inject;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTResetPasswordTask extends GTNetworkTask {

    private UserService userService;

    @Inject
    public GTResetPasswordTask(UserService userService, GTCacheService cacheService) {
        this.userService = userService;
        this.cacheService = cacheService;
    }

    public GTRequestPerformed resetPassword(String email, GTResponseHandler<GTPasswordResetCompleteResult> responseHandler) {
        ResetPasswordData resetPasswordData = new ResetPasswordData(email);
        return performJsonRequest(userService.resetPassword(resetPasswordData), GTPasswordResetCompleteResult.class, responseHandler, false);
    }
}
