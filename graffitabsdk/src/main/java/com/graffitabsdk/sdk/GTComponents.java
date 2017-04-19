package com.graffitabsdk.sdk;

import android.app.Activity;
import android.app.Application;

import com.graffitabsdk.GTMainTesting;
import com.graffitabsdk.api.GTAccountManager;
import com.graffitabsdk.api.GTMeManager;
import com.graffitabsdk.api.GTStreamableManager;
import com.graffitabsdk.api.GTUserManager;
import com.graffitabsdk.sdk.cache.GTCacheService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by georgichristov on 19/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@Singleton
@Component(modules = {GTModuleLocation.class, GTModuleUser.class, GTModuleStreamable.class, GTModuleNotification.class, GTModuleDevice.class, GTModuleNetwork.class, GTModuleApp.class})
interface GTComponents {
    void inject(GTMainTesting mainTesting);
    void inject(Activity activity);
    GTUserManager getUserManager();
    GTMeManager getMeManager();
    GTAccountManager getAccountManager();
    GTCacheService getCacheService();
    GTStreamableManager getStreamableManager();

    static final class Initializer {
        private Initializer() {}
        public static GTComponents init(Application app, GTConfig gtConfig) {
            return DaggerGTComponents.builder()
                    .gTModuleApp(new GTModuleApp(app))
                    .gTModuleNetwork(new GTModuleNetwork(gtConfig))
                    .build();
        }
    }
}
