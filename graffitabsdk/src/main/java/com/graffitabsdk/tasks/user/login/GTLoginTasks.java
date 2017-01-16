package com.graffitabsdk.tasks.user.login;

import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.common.GTResponse;
import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.network.service.user.data.LoginData;
import com.graffitabsdk.network.service.user.UserService;
import com.graffitabsdk.tasks.common.GTNetworkTask;

import javax.inject.Inject;

/**
 * Created by david on 10/12/2016.
 */

public class GTLoginTasks extends GTNetworkTask<GTUser> {

    private UserService userService;
    private LoggedInUserPersistor loggedInUserPersistor;

    @Inject
    public GTLoginTasks(UserService userService, LoggedInUserPersistor loggedInUserPersistor) {
        this.userService = userService;
        this.loggedInUserPersistor = loggedInUserPersistor;
    }

    public RequestPerformed<GTUser> login(String username, String password,
                                          GTResponseHandler<GTUser> responseHandler) {
        LoginData loginData = new LoginData(username, password);
        return performJsonRequest(userService.login(loginData), GTUser.class, "user", responseHandler, false);
    }

    public RequestPerformed<GTUser> loginWithExternalProvider() {
        //TODO
        return null;
    }

    @Override
    protected void performExtraOperationOnSuccess(GTResponse<GTUser> gtResponse) {
        loggedInUserPersistor.saveLoggedInUser(gtResponse.getObject());
    }
}
