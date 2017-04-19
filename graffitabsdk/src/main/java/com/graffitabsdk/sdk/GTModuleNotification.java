package com.graffitabsdk.sdk;

import com.graffitabsdk.network.service.notification.GTNotificationService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by georgichristov on 18/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@Module(includes = GTModuleNetwork.class)
class GTModuleNotification {
    @Provides
    @Singleton
    GTNotificationService provideNotificationService(Retrofit retrofit) {
        return retrofit.create(GTNotificationService.class);
    }
}
