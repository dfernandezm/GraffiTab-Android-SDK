package com.graffitabsdk.config;

import com.graffitabsdk.network.service.streamable.StreamableService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@Module(includes = NetworkModule.class)
class StreamableModule {
    @Provides
    @Singleton
    StreamableService provideStreamableService(Retrofit retrofit) {
        return retrofit.create(StreamableService.class);
    }
}
