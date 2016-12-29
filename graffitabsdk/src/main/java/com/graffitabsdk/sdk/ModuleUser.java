package com.graffitabsdk.sdk;

import android.app.Application;
import android.support.annotation.Nullable;
import com.google.gson.Gson;
import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.service.assets.AssetService;
import com.graffitabsdk.network.service.user.UserService;
import com.graffitabsdk.network.service.user.persist.LoggedInUserPersistor;
import com.graffitabsdk.network.service.user.persist.SharedPrefsLoggedInUserPersistor;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import javax.inject.Singleton;

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
