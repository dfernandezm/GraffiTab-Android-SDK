package com.graffitabsdk.config.dagger.modules;

import com.graffitabsdk.network.service.notification.NotificationService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by georgichristov on 18/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@Module(includes = NetworkModule.class)
public class NotificationModule {
    @Provides
    @Singleton
    NotificationService provideNotificationService(Retrofit retrofit) {
        return retrofit.create(NotificationService.class);
    }
}
