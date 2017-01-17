package com.graffitabsdk.config.dagger.components;

import android.app.Activity;
import android.app.Application;

import com.graffitabsdk.MainTesting;
import com.graffitabsdk.api.GTAccountManager;
import com.graffitabsdk.api.GTMeManager;
import com.graffitabsdk.api.GTUserManager;
import com.graffitabsdk.config.GTConfig;
import com.graffitabsdk.config.dagger.modules.AppModule;
import com.graffitabsdk.config.dagger.modules.NetworkModule;
import com.graffitabsdk.config.dagger.modules.StreamableModule;
import com.graffitabsdk.config.dagger.modules.UserModule;
import com.graffitabsdk.tasks.cache.GTCacheService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by david on 03/12/2016.
 */

@Singleton
@Component(modules = {UserModule.class, StreamableModule.class, NetworkModule.class, AppModule.class})
public interface UserComponent {
    void inject(MainTesting mainTesting);
    void inject(Activity activity);
    GTUserManager getUserManager();
    GTMeManager getMeManager();
    GTAccountManager getAccountManager();
    GTCacheService getCacheService();

    static final class Initializer {
        private Initializer() {}
        public static UserComponent init(Application app, GTConfig gtConfig) {
            return DaggerUserComponent.builder()
                    .appModule(new AppModule(app))
                    .networkModule(new NetworkModule(gtConfig))
                    .build();
        }
    }
}
