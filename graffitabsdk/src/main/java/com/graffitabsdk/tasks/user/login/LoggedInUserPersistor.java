package com.graffitabsdk.tasks.user.login;

import com.graffitabsdk.model.GTUser;

/**
 * Created by david on 10/12/2016.
 */

public interface LoggedInUserPersistor {
    GTUser getLoggedInUser();
    void saveLoggedInUser(GTUser user);
    void clearLoggedInUser();
}
