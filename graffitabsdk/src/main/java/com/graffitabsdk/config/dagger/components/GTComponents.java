package com.graffitabsdk.config.dagger.components;

import android.app.Activity;
import android.app.Application;

import com.graffitabsdk.MainTesting;
import com.graffitabsdk.api.GTAccountManager;
import com.graffitabsdk.api.GTMeManager;
import com.graffitabsdk.api.GTStreamableManager;
import com.graffitabsdk.api.GTUserManager;
import com.graffitabsdk.config.GTConfig;
import com.graffitabsdk.config.dagger.modules.AppModule;
import com.graffitabsdk.config.dagger.modules.NetworkModule;
import com.graffitabsdk.config.dagger.modules.NotificationModule;
import com.graffitabsdk.config.dagger.modules.StreamableModule;
import com.graffitabsdk.config.dagger.modules.UserModule;
import com.graffitabsdk.tasks.cache.GTCacheService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by georgichristov on 19/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@Singleton
@Component(modules = {UserModule.class, StreamableModule.class, NotificationModule.class, NetworkModule.class, AppModule.class})
public interface GTComponents {
    void inject(MainTesting mainTesting);
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
                    .appModule(new AppModule(app))
                    .networkModule(new NetworkModule(gtConfig))
                    .build();
        }
    }
}
