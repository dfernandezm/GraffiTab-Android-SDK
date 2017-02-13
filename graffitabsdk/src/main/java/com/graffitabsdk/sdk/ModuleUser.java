package com.graffitabsdk.sdk;

import android.app.Application;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.service.assets.AssetService;
import com.graffitabsdk.network.service.user.UserService;
import com.graffitabsdk.network.service.user.persist.AccountsPersistor;
import com.graffitabsdk.network.service.user.persist.SecureAccountsPersistor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by david on 03/12/2016.
 */
@Module(includes = ModuleNetwork.class)
class ModuleUser {
    @Provides
    @Singleton
    UserService provideUserService(Retrofit retrofit) {
         return retrofit.create(UserService.class);
    }

    @Provides
    @Singleton
    AssetService provideAssetService(Retrofit retrofit) {
        return retrofit.create(AssetService.class);
    }

    @Provides
    @Singleton
    AccountsPersistor provideLoggedInUserPersistor(@Nullable  Application application, Gson gson) {
        //TODO: Remove once the tests are in place (subclass module with mock)
        if (application == null) {
            return new AccountsPersistor() {

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
            return new SecureAccountsPersistor(application, gson);
        }
    }
}
