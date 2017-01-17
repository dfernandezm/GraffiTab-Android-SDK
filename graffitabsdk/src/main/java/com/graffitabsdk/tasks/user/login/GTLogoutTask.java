package com.graffitabsdk.tasks.user.login;

import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.network.service.user.UserService;
import com.graffitabsdk.tasks.cache.GTCacheService;
import com.graffitabsdk.tasks.common.GTNetworkTask;

import javax.inject.Inject;

/**
 * Created by david on 10/12/2016.
 */

public class GTLogoutTask extends GTNetworkTask {

    private UserService userService;
    private LoggedInUserPersistor loggedInUserPersistor;
    private GTCacheService cacheService;

    @Inject
    public GTLogoutTask(UserService userService, LoggedInUserPersistor loggedInUserPersistor, GTCacheService cacheService) {
        this.userService = userService;
        this.cacheService = cacheService;
        this.loggedInUserPersistor = loggedInUserPersistor;
    }

    public RequestPerformed logout(GTResponseHandler<Void> responseHandler) {
        return performRawRequest(userService.logout(), responseHandler);
    }

    @Override
    protected void performExtraOperationOnSuccess(GTResponse<?> gtResponse) {
        loggedInUserPersistor.clearLoggedInUser();
        cacheService.invalidateCache();
    }
}
