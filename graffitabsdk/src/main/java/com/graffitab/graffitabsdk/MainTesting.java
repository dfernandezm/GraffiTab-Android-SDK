package com.graffitab.graffitabsdk;

import com.graffitab.graffitabsdk.managers.api.GTUserManager;
import com.graffitab.graffitabsdk.model.GTUser;
import com.graffitab.graffitabsdk.network.common.GTResponseObject;
import com.graffitab.graffitabsdk.network.common.ResponseHandler;

/**
 * Created by david on 06/11/2016.
 */
public class MainTesting {
    
    public static void main(String[] args) throws Exception {
        GTUserManager userManager = new GTUserManager();

        ResponseHandler<GTUser> responseHandler = new ResponseHandler<GTUser>() {
            @Override
            public void onSuccess(GTResponseObject<GTUser> responseObject) {
                GTUser user = responseObject.getObject();
                System.out.println( "Invoked endpoint: " + responseObject.getApiEndpointUrl());
                System.out.println("- email:         " + user.email);
                System.out.println("- username:      " + user.username);
                System.out.println("- firstname:     " + user.firstName);
                System.out.println("- lastname:      " + user.lastName);
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
}
