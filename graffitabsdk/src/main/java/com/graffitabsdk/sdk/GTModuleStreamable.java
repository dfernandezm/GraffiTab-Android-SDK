package com.graffitabsdk.sdk;

import com.graffitabsdk.network.service.streamable.GTStreamableService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@Module(includes = GTModuleNetwork.class)
class GTModuleStreamable {
    @Provides
    @Singleton
    GTStreamableService provideStreamableService(Retrofit retrofit) {
        return retrofit.create(GTStreamableService.class);
    }
}
