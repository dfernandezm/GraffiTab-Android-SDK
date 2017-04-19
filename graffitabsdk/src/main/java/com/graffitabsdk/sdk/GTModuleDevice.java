package com.graffitabsdk.sdk;

import com.graffitabsdk.network.service.device.GTDeviceService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by georgichristov on 02/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@Module(includes = GTModuleNetwork.class)
class GTModuleDevice {
    @Provides
    @Singleton
    GTDeviceService provideDeviceService(Retrofit retrofit) {
        return retrofit.create(GTDeviceService.class);
    }
}
