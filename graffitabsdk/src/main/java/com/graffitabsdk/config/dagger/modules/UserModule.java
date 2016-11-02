package com.graffitabsdk.config.dagger.modules;

import com.graffitabsdk.network.service.user.UserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by david on 03/12/2016.
 */

@Module(includes = NetworkModule.class)
public class UserModule {
    @Provides
    @Singleton
    UserService provideUserService(Retrofit retrofit) {
         return retrofit.create(UserService.class);
    }
}
