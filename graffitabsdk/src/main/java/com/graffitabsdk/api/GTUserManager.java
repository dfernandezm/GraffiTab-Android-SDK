package com.graffitabsdk.api;

import com.graffitabsdk.network.common.RequestPerformed;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.result.GTPasswordResetCompleteResult;
import com.graffitabsdk.network.common.result.GTRegistrationCompleteResult;
import com.graffitabsdk.network.service.user.response.GTUserResponse;
import com.graffitabsdk.tasks.user.GTUserTasks;
import com.graffitabsdk.tasks.user.login.GTLoginTasks;
import com.graffitabsdk.tasks.user.login.GTLogoutTask;
import com.graffitabsdk.tasks.user.login.GTRegisterTask;
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
    private GTRegisterTask gtRegisterTask;

    @Inject
    public GTUserManager(GTUserTasks userTasks, GTLoginTasks loginTasks, GTLogoutTask gtLogoutTask, GTResetPasswordTask gtResetPasswordTask, GTRegisterTask gtRegisterTask) {
        this.gtUserTasks = userTasks;
        this.gtLoginTasks = loginTasks;
        this.gtLogoutTask = gtLogoutTask;
        this.gtResetPasswordTask = gtResetPasswordTask;
        this.gtRegisterTask = gtRegisterTask;
    }

    public RequestPerformed login(String username, String password, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtLoginTasks.login(username, password, responseHandler);
    }

    public RequestPerformed logout(GTResponseHandler<Void> responseHandler) {
        return gtLogoutTask.logout(responseHandler);
    }

    public RequestPerformed resetPassword(String email, GTResponseHandler<GTPasswordResetCompleteResult> responseHandler) {
        return gtResetPasswordTask.resetPassword(email, responseHandler);
    }

    public RequestPerformed register(String firstName, String lastName, String email, String username, String password, GTResponseHandler<GTRegistrationCompleteResult> responseHandler) {
        return gtRegisterTask.register(firstName, lastName, email, username, password, responseHandler);
    }
}
