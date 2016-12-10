package com.graffitabsdk.api;

import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.tasks.user.GTUserTasks;
import com.graffitabsdk.tasks.user.login.GTLoginTasks;
import com.graffitabsdk.tasks.user.login.GTLogoutTask;

import javax.inject.Inject;

/**
 * Created by david on 09/11/2016.
 */

public class GTUserManager {

    private GTLoginTasks gtUserLogin;
    private GTUserTasks gtUserTask;
    private GTLogoutTask gtLogoutTask;

    @Inject
    public GTUserManager(GTUserTasks userTasks, GTLoginTasks loginTasks, GTLogoutTask gtLogoutTask) {
        this.gtUserTask = userTasks;
        this.gtUserLogin = loginTasks;
        this.gtLogoutTask = gtLogoutTask;
    }

    public RequestPerformed<GTUser> login(String username, String password,
                                          GTResponseHandler<GTUser> responseHandler) {
        return gtUserLogin.login(username, password, responseHandler);
    }

    public RequestPerformed<GTUser> getMe(GTResponseHandler<GTUser> responseHandler) {
        return gtUserTask.getMe(responseHandler);
    }

    public RequestPerformed<Void> logout(GTResponseHandler<Void> responseHandler) {
        return gtLogoutTask.logout(responseHandler);
    }
}
