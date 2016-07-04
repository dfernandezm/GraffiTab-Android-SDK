package com.graffitab.graffitabsdk.managers;

import com.graffitab.graffitabsdk.config.GTConfig;
import com.graffitab.graffitabsdk.config.GTSDKConfig;
import com.graffitab.graffitabsdk.log.GTLog;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTApiManager {

    private static GTApiManager sharedInstance = new GTApiManager();

    private Retrofit retrofit;

    private GTApiManager() {
        GTConfig config = GTSDKConfig.sharedInstance.getConfig();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(config.buildApiUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GTLog.i(getClass().getSimpleName(), "Initialized manager with base url - " + retrofit.baseUrl(), false);
    }
}
