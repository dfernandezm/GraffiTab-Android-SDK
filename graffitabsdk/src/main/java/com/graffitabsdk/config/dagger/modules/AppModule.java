package com.graffitabsdk.config.dagger.modules;

import android.app.Application;
import android.support.annotation.Nullable;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by david on 03/12/2016.
 */


@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    @Nullable
    Application providesApplication() {
        return mApplication;
    }
}

