package com.graffitabsdk.config;

import com.graffitabsdk.network.service.location.LocationService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by georgichristov on 20/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@Module(includes = NetworkModule.class)
class LocationModule {
    @Provides
    @Singleton
    LocationService provideLocationService(Retrofit retrofit) {
        return retrofit.create(LocationService.class);
    }
}
