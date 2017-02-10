package com.graffitabsdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.assets.response.GTAssetResponse;
import com.graffitabsdk.network.service.user.response.GTUserResponse;
import com.graffitabsdk.sdk.GTConfig;
import com.graffitabsdk.sdk.GTSDK;

import java.io.File;

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
        final long startTime = System.currentTimeMillis();
        GTResponseHandler<GTUserResponse> responseHandler = new GTResponseHandler<GTUserResponse>() {
            @Override
            public void onSuccess(GTResponse<GTUserResponse> gtResponse) {
                GTUser user = gtResponse.getObject().user;
                System.out.println("Invoked endpoint: " + gtResponse.getApiEndpointUrl());
                System.out.println("- email:         " + user.email);
                System.out.println("- username:      " + user.username);
                System.out.println("- firstname:     " + user.firstName);
                System.out.println("- lastname:      " + user.lastName);
                System.out.println("User logged in: " + GTSDK.getAccountManager().isUserLoggedIn());
                final long interm = (System.currentTimeMillis() - startTime);
                final long start2 = System.currentTimeMillis();
                System.out.println("Took: " + interm + " total: " + interm);
                GTSDK.getMeManager().getMe(false, new GTResponseHandler<GTUserResponse>() {
                    @Override
                    public void onSuccess(GTResponse<GTUserResponse> gtResponse) {
                        GTUser user = gtResponse.getObject().user;
                        System.out.println("Invoked endpoint: " + gtResponse.getApiEndpointUrl());
                        System.out.println("- firstname:     " + user.firstName);
                        System.out.println("- lastname:      " + user.lastName);
                        System.out.println("Logged in user: " + GTSDK.getAccountManager().getLoggedInUser());
                        final long interm2 = (System.currentTimeMillis() - start2);
                        final long start3 = System.currentTimeMillis();
                        System.out.println("Took: " + interm2 + " total: " + (System.currentTimeMillis() - startTime));
                        GTSDK.getUserManager().logout(new GTResponseHandler<Void>() {
                            @Override
                            public void onSuccess(GTResponse<Void> gtResponse) {
                                final long interm3 = (System.currentTimeMillis() - start3);
                                System.out.println("Took: " + interm3 + " total: " + (System.currentTimeMillis() - startTime));
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
                    public void onFailure(GTResponse<GTUserResponse> responseObject) {
                        System.out.println("Failure detected");
                        System.out.println("Invoked endpoint: " + responseObject.getApiEndpointUrl());
                        System.out.println("Error: " + responseObject.getResultCode() +
                                " - " + responseObject.getResultDetail());
                    }
                });
            }

            @Override
            public void onFailure(GTResponse<GTUserResponse> responseObject) {
                System.out.println("Failure detected");
                System.out.println("Invoked endpoint: " + responseObject.getApiEndpointUrl());
                System.out.println("Error: " + responseObject.getResultCode() +
                        " - " + responseObject.getResultDetail());
            }
        };


        GTSDK.getUserManager().login("david", "password1", responseHandler);
    }


    private void performCachedCalls() {
        GTResponseHandler<GTUserResponse> responseHandler = new GTResponseHandler<GTUserResponse>() {

            @Override
            public void onSuccess(GTResponse<GTUserResponse> gtResponse) {
                System.out.println("Logged in");
                final long startTime = System.currentTimeMillis();
                GTSDK.getMeManager().getMe(false, new GTResponseHandler<GTUserResponse>() {

                    @Override
                    public void onSuccess(GTResponse<GTUserResponse> gtResponse) {
                        System.out.println("Get me (1)");
                        final long interm = (System.currentTimeMillis() - startTime);
                        final long start2 = System.currentTimeMillis();
                        System.out.println("Took: " + interm);

                        GTSDK.getMeManager().getMe(true, new GTResponseHandler<GTUserResponse>() {

                            @Override
                            public void onSuccess(GTResponse<GTUserResponse> gtResponse) {
                                System.out.println("Get me (2)");
                                GTUser user = gtResponse.getObject().user;
                                System.out.println("Invoked endpoint: " + gtResponse.getApiEndpointUrl());
                                System.out.println("- firstname:     " + user.firstName);
                                System.out.println("- lastname:      " + user.lastName);
                                System.out.println("Logged in user: " + GTSDK.getAccountManager().getLoggedInUser());
                                final long interm = (System.currentTimeMillis() - start2);
                                System.out.println("Took: " + interm);
                            }

                            @Override
                            public void onFailure(GTResponse<GTUserResponse> gtResponse) {
                                System.out.println("Failure detected");
                                System.out.println("Invoked endpoint: " + gtResponse.getApiEndpointUrl());
                                System.out.println("Error: " + gtResponse.getResultCode() +
                                        " - " + gtResponse.getResultDetail());
                            }

                            @Override
                            public void onCache(GTResponse<GTUserResponse> gtResponse) {
                                System.out.println("Got cached response");
                                GTUser user = gtResponse.getObject().user;
                                System.out.println("Invoked endpoint: " + gtResponse.getApiEndpointUrl());
                                System.out.println("- firstname:     " + user.firstName);
                                System.out.println("- lastname:      " + user.lastName);
                                System.out.println("Logged in user: " + GTSDK.getAccountManager().getLoggedInUser());
                                final long interm = (System.currentTimeMillis() - start2);
                                System.out.println("Took: " + interm);
                            }

                        });
                    }

                    @Override
                    public void onFailure(GTResponse<GTUserResponse> gtResponse) {
                        System.out.println("Failure detected");
                        System.out.println("Invoked endpoint: " + gtResponse.getApiEndpointUrl());
                        System.out.println("Error: " + gtResponse.getResultCode() +
                                " - " + gtResponse.getResultDetail());
                    }
                });
            }

            @Override
            public void onFailure(GTResponse<GTUserResponse> gtResponse) {
                System.out.println("Failure detected");
                System.out.println("Invoked endpoint: " + gtResponse.getApiEndpointUrl());
                System.out.println("Error: " + gtResponse.getResultCode() +
                        " - " + gtResponse.getResultDetail());
            }
        };
        GTSDK.getUserManager().login("david", "password1", responseHandler);
    }

    public void uploadAvatar() {
        GTResponseHandler<GTUserResponse> responseHandler = new GTResponseHandler<GTUserResponse>() {

            @Override
            public void onSuccess(GTResponse<GTUserResponse> gtResponse) {
                GTSDK.getMeManager().uploadAvatar(getBitmapFromFile(), new GTResponseHandler<GTAssetResponse>() {
                    @Override
                    public void onSuccess(GTResponse<GTAssetResponse> gtResponse) {
                        GTAssetResponse asset = gtResponse.getObject();
                        System.out.println("Invoked endpoint: " + gtResponse.getApiEndpointUrl());
                        System.out.println("- asset GUID:     " + asset.asset.guid);
                        System.out.println("- asset Link:      " + asset.asset.link);
                    }

                    @Override
                    public void onFailure(GTResponse<GTAssetResponse> gtResponse) {
                        System.out.println("Failure detected");
                        System.out.println("Invoked endpoint: " + gtResponse.getApiEndpointUrl());
                        System.out.println("Error: " + gtResponse.getResultCode() +
                                " - " + gtResponse.getResultDetail());
                    }
                });
            }

            @Override
            public void onFailure(GTResponse<GTUserResponse> gtResponse) {
                System.out.println("Failure detected");
                System.out.println("Invoked endpoint: " + gtResponse.getApiEndpointUrl());
                System.out.println("Error: " + gtResponse.getResultCode() +
                        " - " + gtResponse.getResultDetail());
            }
        };

        GTSDK.getUserManager().login("david", "password1", responseHandler);
    }

    private Bitmap getBitmapFromFile() {
        File f = new File("/Users/david/upload.jpg");
        // Needs Android instrumentation -- won't work here
        final BitmapFactory.Options options = new BitmapFactory.Options();
        return BitmapFactory.decodeFile( f.getPath(), options );
    }

    public static void main(String[] args) throws Exception {
        MainTesting mainTesting = new MainTesting();
        //mainTesting.performCall();
        //mainTesting.performCachedCalls();
        mainTesting.uploadAvatar();
    }
}
