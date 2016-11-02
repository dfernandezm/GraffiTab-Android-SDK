package com.graffitabsdk.tasks.user.login;

import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.network.service.user.LoginData;
import com.graffitabsdk.network.service.user.UserService;
import com.graffitabsdk.tasks.GTNetworkTask;

import javax.inject.Inject;

/**
 * Created by david on 01/12/2016.
 */

public class GTUserTasks extends GTNetworkTask<GTUser> {

    private UserService userService;

    @Inject
    public GTUserTasks(UserService userService) {
        this.userService = userService;
    }

    public RequestPerformed<GTUser> login(String username, String password,
                                          GTResponseHandler<GTUser> responseHandler) {
        setResponseProperties(responseHandler, "user");
        LoginData loginData = new LoginData(username, password);
        return performRequest(userService.login(loginData));
    }

    public RequestPerformed<GTUser> getMe(GTResponseHandler<GTUser> responseHandler) {
        setResponseProperties(responseHandler, "user");
        return performRequest(userService.getMe());
    }
}
