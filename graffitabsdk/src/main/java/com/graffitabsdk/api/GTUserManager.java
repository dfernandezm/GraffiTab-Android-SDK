package com.graffitabsdk.api;

import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.common.GTResponseHandler;
import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.tasks.user.GTUserTasks;
import com.graffitabsdk.tasks.user.login.GTLoginTasks;
import com.graffitabsdk.tasks.user.login.GTLogoutTask;
import com.graffitabsdk.tasks.user.login.GTResetPasswordTask;

import javax.inject.Inject;

/**
 * Created by david on 09/11/2016.
 */

public class GTUserManager {

    private GTLoginTasks gtLoginTasks;
    private GTUserTasks gtUserTasks;
    private GTLogoutTask gtLogoutTask;
    private GTResetPasswordTask gtResetPasswordTask;

    @Inject
    public GTUserManager(GTUserTasks userTasks, GTLoginTasks loginTasks, GTLogoutTask gtLogoutTask, GTResetPasswordTask gtResetPasswordTask) {
        this.gtUserTasks = userTasks;
        this.gtLoginTasks = loginTasks;
        this.gtLogoutTask = gtLogoutTask;
        this.gtResetPasswordTask = gtResetPasswordTask;
    }

    public RequestPerformed<GTUser> login(String username, String password,
                                          GTResponseHandler<GTUser> responseHandler) {
        return gtLoginTasks.login(username, password, responseHandler);
    }

    public RequestPerformed<Void> logout(GTResponseHandler<Void> responseHandler) {
        return gtLogoutTask.logout(responseHandler);
    }

    public RequestPerformed<String> resetPassword(String email, GTResponseHandler<String> responseHandler) {
        return gtResetPasswordTask.resetPassword(email, responseHandler);
    }

    public RequestPerformed<GTUser> getMe(GTResponseHandler<GTUser> responseHandler) {
        return gtUserTasks.getMe(responseHandler);
    }

    public RequestPerformed<GTUser> getMyFullProfile(GTResponseHandler<GTUser> responseHandler) {
        return gtUserTasks.getMyFullProfile(responseHandler);
    }
}
