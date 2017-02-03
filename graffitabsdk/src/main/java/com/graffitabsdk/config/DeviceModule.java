package com.graffitabsdk.config;

import com.graffitabsdk.network.service.device.DeviceService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by georgichristov on 02/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@Module(includes = NetworkModule.class)
class DeviceModule {
    @Provides
    @Singleton
    DeviceService provideDeviceService(Retrofit retrofit) {
        return retrofit.create(DeviceService.class);
    }
}
