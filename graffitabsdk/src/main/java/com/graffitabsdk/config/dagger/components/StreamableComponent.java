package com.graffitabsdk.config.dagger.components;

import android.app.Activity;
import android.app.Application;

import com.graffitabsdk.MainTesting;
import com.graffitabsdk.api.GTStreamableManager;
import com.graffitabsdk.config.GTConfig;
import com.graffitabsdk.config.dagger.modules.AppModule;
import com.graffitabsdk.config.dagger.modules.NetworkModule;
import com.graffitabsdk.config.dagger.modules.StreamableModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by georgichristov on 17/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@Singleton
@Component(modules = {StreamableModule.class, NetworkModule.class, AppModule.class})
public interface StreamableComponent {
    void inject(MainTesting mainTesting);
    void inject(Activity activity);
    GTStreamableManager getStreamableManager();

    static final class Initializer {
        private Initializer() {}
        public static StreamableComponent init(Application app, GTConfig gtConfig) {
            return DaggerStreamableComponent.builder()
                    .appModule(new AppModule(app))
                    .networkModule(new NetworkModule(gtConfig))
                    .build();
        }
    }
}
