package com.graffitabsdk.api;

import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.tasks.user.login.LoggedInUserPersistor;

import javax.inject.Inject;

/**
 * Created by david on 10/12/2016.
 */

public class GTAccountManager {

    private LoggedInUserPersistor loggedInUserPersistor;

    @Inject
    public GTAccountManager(LoggedInUserPersistor loggedInUserPersistor) {
        this.loggedInUserPersistor = loggedInUserPersistor;
    }

    public GTUser getLoggedInUser() {
        return loggedInUserPersistor.getLoggedInUser();
    }

    public boolean isUserLoggedIn() {
        return loggedInUserPersistor.getLoggedInUser() != null;
    }
}
