package com.graffitabsdk.api;

import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.service.user.persist.GTAccountsPersistor;

import javax.inject.Inject;

/**
 * Created by david on 10/12/2016.
 */

public class GTAccountManager {

    private GTAccountsPersistor accountsPersistor;

    @Inject
    public GTAccountManager(GTAccountsPersistor accountsPersistor) {
        this.accountsPersistor = accountsPersistor;
    }

    public GTUser getLoggedInUser() {
        return accountsPersistor.getLoggedInUser();
    }

    public boolean isUserLoggedIn() {
        return accountsPersistor.getLoggedInUser() != null;
    }

    public boolean hasLastLoggedInUser() {
        return accountsPersistor.getLastLoggedInUser() != null;
    }

    public GTUser getLastLoggedInUser() {
        return accountsPersistor.getLastLoggedInUser();
    }

    public String getLastLoggedInUserPassword() {
        return accountsPersistor.getLastLoggedInUserPassword();
    }

    public void clearLastLoggedInUser() {
        accountsPersistor.clearLastLoggedInUser();
    }
}
