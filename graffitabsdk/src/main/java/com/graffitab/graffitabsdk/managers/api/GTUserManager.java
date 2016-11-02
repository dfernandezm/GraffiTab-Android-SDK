package com.graffitab.graffitabsdk.managers.api;

import com.graffitab.graffitabsdk.model.GTUser;
import com.graffitab.graffitabsdk.network.common.RequestPerformed;
import com.graffitab.graffitabsdk.network.common.ResponseHandler;
import com.graffitab.graffitabsdk.tasks.user.login.GTLoginTask;

/**
 * Created by david on 09/11/2016.
 */

public class GTUserManager {

    public RequestPerformed<GTUser> login(String username, String password,
                                          ResponseHandler<GTUser> responseHandler) {
        GTLoginTask loginTask = new GTLoginTask();
        RequestPerformed<GTUser> requestPerformed = loginTask.login(username, password, responseHandler);
        return requestPerformed;
    }
}
