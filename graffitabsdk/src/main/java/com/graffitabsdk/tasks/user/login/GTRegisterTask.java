package com.graffitabsdk.tasks.user.login;

import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.result.GTRegistrationCompleteResult;
import com.graffitabsdk.network.service.user.UserService;
import com.graffitabsdk.network.service.user.data.register.RegisterData;
import com.graffitabsdk.network.service.user.data.register.RegisterUserData;
import com.graffitabsdk.tasks.cache.GTCacheService;
import com.graffitabsdk.tasks.common.GTNetworkTask;

import javax.inject.Inject;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTRegisterTask extends GTNetworkTask {

    private UserService userService;

    @Inject
    public GTRegisterTask(UserService userService, GTCacheService cacheService) {
        this.userService = userService;
        this.cacheService = cacheService;
    }

    public GTRequestPerformed register(String firstName, String lastName, String email, String username, String password, GTResponseHandler<GTRegistrationCompleteResult> responseHandler) {
        RegisterUserData registerUserData = new RegisterUserData(firstName, lastName, email, username, password);
        RegisterData registerData = new RegisterData(registerUserData);
        return performJsonRequest(userService.register(registerData), GTRegistrationCompleteResult.class, responseHandler, false);
    }
}
