package com.graffitab.graffitabsdk;

import com.graffitab.graffitabsdk.log.GTLog;
import com.graffitab.graffitabsdk.managers.api.GTUserManager;
import com.graffitab.graffitabsdk.model.GTUser;
import com.graffitab.graffitabsdk.network.common.GTResponseObject;
import com.graffitab.graffitabsdk.network.common.ResponseHandler;

import org.junit.Test;

/**
 * Created by david on 22/11/2016.
 */

public class UserServiceTest {

    //TODO: perform validation in 2-steps for the async call: 1 step submit, 2 step callback test
    // or create a blocking call to wait for the callbacks to resolve
    // Also, mock Retrofit
    @Test
    public void successfulLogin() {
        GTUserManager userManager = new GTUserManager();
        GTUser result = new GTUser();

        ResponseHandler<GTUser> responseHandler = new ResponseHandler<GTUser>() {
            @Override
            public void onSuccess(GTResponseObject<GTUser> responseObject) {
                GTUser user = responseObject.getObject();
                GTLog.i("TEST", "Invoked endpoint: " + responseObject.getApiEndpointUrl(), true);
                System.out.println("- email:         " + user.email);
                System.out.println("- username:      " + user.username);
                System.out.println("- firstname:     " + user.firstName);
                System.out.println("- lastname:      " + user.lastName);
                result.email = user.email;
            }

            @Override
            public void onFailure(GTResponseObject<GTUser> responseObject) {
                System.out.println("Failure detected");
                System.out.println("Invoked endpoint: " + responseObject.getApiEndpointUrl());
                System.out.println("Error: " + responseObject.getResultCode() +
                        " - " + responseObject.getResultDetail());
            }
        };

        userManager.login("david", "password1", responseHandler);
    }

    public static void main(String[] args) throws Exception {
        UserServiceTest userServiceTest = new UserServiceTest();
        userServiceTest.successfulLogin();
    }
}
