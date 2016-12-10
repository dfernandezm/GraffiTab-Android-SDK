package com.graffitabsdk.tasks.user;

import com.google.gson.Gson;
import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.network.service.user.UserService;
import com.graffitabsdk.tasks.common.GTNetworkTask;

import javax.inject.Inject;

/**
 * Created by david on 01/12/2016.
 */

public class GTUserTasks extends GTNetworkTask<GTUser> {

    private UserService userService;

    @Inject
    public GTUserTasks(UserService userService, Gson gson) {
        this.userService = userService;
        super.gson = gson;
    }

    public RequestPerformed<GTUser> getMe(GTResponseHandler<GTUser> responseHandler) {
        return performJsonRequest(userService.getMe(), "user", responseHandler);
    }
}
