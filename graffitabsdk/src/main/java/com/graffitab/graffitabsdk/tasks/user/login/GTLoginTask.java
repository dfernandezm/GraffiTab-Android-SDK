package com.graffitab.graffitabsdk.tasks.user.login;

import com.graffitab.graffitabsdk.model.GTUser;
import com.graffitab.graffitabsdk.network.common.RequestPerformed;
import com.graffitab.graffitabsdk.network.common.ResponseHandler;
import com.graffitab.graffitabsdk.network.common.RetrofitServicesManager;
import com.graffitab.graffitabsdk.network.service.user.LoginData;
import com.graffitab.graffitabsdk.network.service.user.UserService;
import com.graffitab.graffitabsdk.tasks.GTNetworkTask;

/**
 * Created by david on 09/11/2016.
 */
public class GTLoginTask extends GTNetworkTask<GTUser> {

    private UserService userService;

    public GTLoginTask() {
        super();
        userService = RetrofitServicesManager.getUserService();
    }

    public RequestPerformed<GTUser> login(String username, String password,
                                          ResponseHandler<GTUser> responseHandler) {
        setResponseProperties(responseHandler);
        LoginData loginData = new LoginData(username, password);
        return performRequest(userService.login(loginData));
    }

    private void setResponseProperties(ResponseHandler<GTUser> responseHandler) {
        super.propertyNameToExtract = "user";
        super.responseHandler = responseHandler;
    }
}
