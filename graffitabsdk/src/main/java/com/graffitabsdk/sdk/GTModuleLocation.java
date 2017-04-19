package com.graffitabsdk.sdk;

import com.graffitabsdk.network.service.location.GTLocationService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by georgichristov on 20/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@Module(includes = GTModuleNetwork.class)
class GTModuleLocation {
    @Provides
    @Singleton
    GTLocationService provideLocationService(Retrofit retrofit) {
        return retrofit.create(GTLocationService.class);
    }
}
