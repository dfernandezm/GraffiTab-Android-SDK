package com.graffitabsdk.sdk;

import android.app.Application;
import android.support.annotation.Nullable;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by david on 03/12/2016.
 */


@Module
class GTModuleApp {

    private Application mApplication;

    public GTModuleApp(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    @Nullable
    Application providesApplication() {
        return mApplication;
    }
}

