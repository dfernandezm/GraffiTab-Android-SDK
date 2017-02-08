package com.graffitabsdk.network.service.user;

import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.user.response.GTUserResponse;
import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.service.user.persist.LoggedInUserPersistor;

import javax.inject.Inject;

/**
 * Created by david on 10/12/2016.
 */

public class GTLoginTasks extends GTNetworkTask {

    private UserService userService;
    private LoggedInUserPersistor loggedInUserPersistor;

    @Inject
    public GTLoginTasks(UserService userService, LoggedInUserPersistor loggedInUserPersistor) {
        this.userService = userService;
        this.loggedInUserPersistor = loggedInUserPersistor;
    }

    public GTRequestPerformed login(String username, String password, GTResponseHandler<GTUserResponse> responseHandler) {
        LoginData loginData = new LoginData(username, password);
        return performJsonRequest(userService.login(loginData), GTUserResponse.class, responseHandler);
    }

    public GTRequestPerformed loginWithExternalProvider() {
        //TODO
        return null;
    }

    @Override
    protected void performExtraOperationOnSuccess(GTResponse<?> gtResponse) {
        loggedInUserPersistor.saveLoggedInUser(((GTUserResponse) gtResponse.getObject()).user);
    }
}