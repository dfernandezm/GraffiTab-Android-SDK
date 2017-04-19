package com.graffitabsdk.sdk;

import android.app.Application;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.service.assets.GTAssetService;
import com.graffitabsdk.network.service.user.GTUserService;
import com.graffitabsdk.network.service.user.persist.GTAccountsPersistor;
import com.graffitabsdk.network.service.user.persist.GTSecureAccountsPersistor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by david on 03/12/2016.
 */
@Module(includes = GTModuleNetwork.class)
class GTModuleUser {
    @Provides
    @Singleton
    GTUserService provideUserService(Retrofit retrofit) {
         return retrofit.create(GTUserService.class);
    }

    @Provides
    @Singleton
    GTAssetService provideAssetService(Retrofit retrofit) {
        return retrofit.create(GTAssetService.class);
    }

    @Provides
    @Singleton
    GTAccountsPersistor provideLoggedInUserPersistor(@Nullable  Application application, Gson gson) {
        //TODO: Remove once the tests are in place (subclass module with mock)
        if (application == null) {
            return new GTAccountsPersistor() {

                private GTUser loggedInUser;
                private GTUser lastLoggedInUser;
                private String lastLoggedInUserPassword;

                @Override
                public GTUser getLoggedInUser() {
                    return loggedInUser;
                }

                @Override
                public void saveLoggedInUser(GTUser user) {
                    this.loggedInUser = user;
                }

                @Override
                public void clearLoggedInUser() {
                    this.loggedInUser = null;
                }

                @Override
                public GTUser getLastLoggedInUser() {
                    return lastLoggedInUser;
                }

                @Override
                public String getLastLoggedInUserPassword() {
                    return lastLoggedInUserPassword;
                }

                @Override
                public void saveLastLoggedInUser(GTUser user) {
                    this.lastLoggedInUser = user;
                }

                @Override
                public void saveLastLoggedInUser(GTUser user, String password) {
                    this.lastLoggedInUser = user;
                    this.lastLoggedInUserPassword = password;
                }

                @Override
                public void clearLastLoggedInUser() {
                    this.lastLoggedInUser = null;
                    this.lastLoggedInUserPassword = null;
                }
            };
        } else {
            return new GTSecureAccountsPersistor(application, gson);
        }
    }
}
