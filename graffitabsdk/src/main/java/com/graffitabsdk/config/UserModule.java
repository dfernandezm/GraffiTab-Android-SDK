package com.graffitabsdk.config;

import android.app.Application;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.service.user.UserService;
import com.graffitabsdk.tasks.login.LoggedInUserPersistor;
import com.graffitabsdk.tasks.login.SharedPrefsLoggedInUserPersistor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by david on 03/12/2016.
 */

@Module(includes = NetworkModule.class)
class UserModule {
    @Provides
    @Singleton
    UserService provideUserService(Retrofit retrofit) {
         return retrofit.create(UserService.class);
    }

    @Provides
    @Singleton
    LoggedInUserPersistor provideLoggedInUserPersistor(@Nullable  Application application, Gson gson) {
        //TODO: Remove once the tests are in place (subclass module with mock)
        if (application == null) {
            return new LoggedInUserPersistor() {
                private GTUser loggedInUser;
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
            };
        } else {
            return new SharedPrefsLoggedInUserPersistor(application, gson);
        }
    }
}