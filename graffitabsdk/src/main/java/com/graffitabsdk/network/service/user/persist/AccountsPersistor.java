package com.graffitabsdk.network.service.user.persist;

import com.graffitabsdk.model.GTUser;

/**
 * Created by david on 10/12/2016.
 */

public interface AccountsPersistor {

    GTUser getLoggedInUser();
    void saveLoggedInUser(GTUser user);
    void clearLoggedInUser();

    GTUser getLastLoggedInUser();
    String getLastLoggedInUserPassword();
    void saveLastLoggedInUser(GTUser user);
    void saveLastLoggedInUser(GTUser user, String password);
    void clearLastLoggedInUser();
}
