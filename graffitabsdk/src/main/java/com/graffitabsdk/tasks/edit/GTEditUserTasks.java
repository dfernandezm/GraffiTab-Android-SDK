package com.graffitabsdk.tasks.edit;

import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.user.UserService;
import com.graffitabsdk.network.service.user.data.edit.EditProfileData;
import com.graffitabsdk.network.service.user.data.edit.EditProfileMetadata;
import com.graffitabsdk.network.service.user.response.GTUserResponse;
import com.graffitabsdk.tasks.login.LoggedInUserPersistor;

import javax.inject.Inject;

/**
 * Created by georgichristov on 31/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTEditUserTasks extends GTNetworkTask {

    private UserService userService;
    private LoggedInUserPersistor loggedInUserPersistor;

    @Inject
    public GTEditUserTasks(UserService userService, LoggedInUserPersistor loggedInUserPersistor) {
        this.userService = userService;
        this.loggedInUserPersistor = loggedInUserPersistor;
    }

    public GTRequestPerformed edit(String firstName, String lastName, String email, String about, String website, GTResponseHandler<GTUserResponse> responseHandler) {
        EditProfileMetadata editProfileMetadata = new EditProfileMetadata(firstName, lastName, email, about, website);
        EditProfileData editProfileData = new EditProfileData(editProfileMetadata);
        return performJsonRequest(userService.edit(editProfileData), GTUserResponse.class, responseHandler);
    }

    @Override
    protected void performExtraOperationOnSuccess(GTResponse<?> gtResponse) {
        loggedInUserPersistor.saveLoggedInUser(((GTUserResponse) gtResponse.getObject()).user);
    }
}
