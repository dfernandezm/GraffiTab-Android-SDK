package com.graffitabsdk;

import com.graffitabsdk.config.GTConfig;
import com.graffitabsdk.config.GTSDK;
import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.common.GTResponse;
import com.graffitabsdk.network.common.GTResponseHandler;

/**
 * Created by david on 06/11/2016.
 */
public class MainTesting {

    public MainTesting() {
        // With injection
        // UserComponent.Initializer.init(null, GTConfig.defaultConfig()).inject(this);
        GTConfig config = GTConfig.defaultConfig();
        config.domain = "dev.graffitab.com";
        GTSDK.init(null, config);
    }

    public void performCall() {

        GTResponseHandler<GTUser> responseHandler = new GTResponseHandler<GTUser>() {
            @Override
            public void onSuccess(GTResponse<GTUser> gtResponse) {
                GTUser user = gtResponse.getObject();
                System.out.println("Invoked endpoint: " + gtResponse.getApiEndpointUrl());
                System.out.println("- email:         " + user.email);
                System.out.println("- username:      " + user.username);
                System.out.println("- firstname:     " + user.firstName);
                System.out.println("- lastname:      " + user.lastName);
                System.out.println("User logged in: " + GTSDK.getAccountManager().isUserLoggedIn());

                GTSDK.getUserManager().getMe(new GTResponseHandler<GTUser>() {
                    @Override
                    public void onSuccess(GTResponse<GTUser> gtResponse) {
                        GTUser user = gtResponse.getObject();
                        System.out.println("Invoked endpoint: " + gtResponse.getApiEndpointUrl());
                        System.out.println("- firstname:     " + user.firstName);
                        System.out.println("- lastname:      " + user.lastName);
                        System.out.println("Logged in user: " + GTSDK.getAccountManager().getLoggedInUser());

                        GTSDK.getUserManager().logout(new GTResponseHandler<Void>() {
                            @Override
                            public void onSuccess(GTResponse<Void> gtResponse) {
                                System.out.println("========= LOGGED OUT ========");
                                System.out.println("User logged in: " + GTSDK.getAccountManager().isUserLoggedIn());
                            }

                            @Override
                            public void onFailure(GTResponse<Void> responseObject) {
                                System.out.println("========= FAILED LOGOUT ========");
                            }
                        });
                    }

                    @Override
                    public void onFailure(GTResponse<GTUser> responseObject) {
                        System.out.println("Failure detected");
                        System.out.println("Invoked endpoint: " + responseObject.getApiEndpointUrl());
                        System.out.println("Error: " + responseObject.getResultCode() +
                                " - " + responseObject.getResultDetail());
                    }
                });
            }

            @Override
            public void onFailure(GTResponse<GTUser> responseObject) {
                System.out.println("Failure detected");
                System.out.println("Invoked endpoint: " + responseObject.getApiEndpointUrl());
                System.out.println("Error: " + responseObject.getResultCode() +
                        " - " + responseObject.getResultDetail());
            }
        };

        GTSDK.getUserManager().login("david", "password1", responseHandler);
    }

    public static void main(String[] args) throws Exception {
        MainTesting mainTesting = new MainTesting();
        mainTesting.performCall();
    }
}
