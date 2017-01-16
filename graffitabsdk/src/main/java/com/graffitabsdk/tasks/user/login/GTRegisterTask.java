package com.graffitabsdk.tasks.user.login;

import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.RequestPerformed;
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
public class GTRegisterTask extends GTNetworkTask<String> {

    private UserService userService;

    @Inject
    public GTRegisterTask(UserService userService, GTCacheService cacheService) {
        this.userService = userService;
        this.cacheService = cacheService;
    }

    public RequestPerformed<String> register(String firstName, String lastName, String email, String username, String password, GTResponseHandler<String> responseHandler) {
        RegisterUserData registerUserData = new RegisterUserData(firstName, lastName, email, username, password);
        RegisterData registerData = new RegisterData(registerUserData);
        return performJsonRequest(userService.register(registerData), String.class, null, responseHandler, false);
    }
}
