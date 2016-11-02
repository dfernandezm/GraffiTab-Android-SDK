package com.graffitabsdk.api;

import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.tasks.user.login.GTUserTasks;

import javax.inject.Inject;

/**
 * Created by david on 09/11/2016.
 */

public class GTUserManager {

    private GTUserTasks gtUserTask;

    @Inject
    public GTUserManager(GTUserTasks userTask) {
        this.gtUserTask = userTask;
    }

    public RequestPerformed<GTUser> login(String username, String password,
                                          GTResponseHandler<GTUser> responseHandler) {
        return gtUserTask.login(username, password, responseHandler);
    }

    public RequestPerformed<GTUser> getMe(GTResponseHandler<GTUser> responseHandler) {
        return gtUserTask.getMe(responseHandler);
    }
}
