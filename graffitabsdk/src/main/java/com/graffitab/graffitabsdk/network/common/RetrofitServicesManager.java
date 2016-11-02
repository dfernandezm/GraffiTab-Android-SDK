package com.graffitab.graffitabsdk.network.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.graffitab.graffitabsdk.config.GTConfig;
import com.graffitab.graffitabsdk.config.GTSDKConfig;
import com.graffitab.graffitabsdk.constants.GTApiConstants;
import com.graffitab.graffitabsdk.log.GTLog;
import com.graffitab.graffitabsdk.network.service.user.UserService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 10/11/2016.
 */
public class RetrofitServicesManager {

    private static Retrofit retrofit;
    private static UserService userService;

    public static UserService getUserService() {
        if (userService == null) {
            userService = getRetrofit().create(UserService.class);
        }
        return userService;
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            setupRetrofit();
        }
        return retrofit;
    }

    private static void setupRetrofit() {
        GTConfig config = GTSDKConfig.get();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setDateFormat(GTApiConstants.GT_API_DATE_FORMAT)
                .create();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(config.buildBaseApiUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GTLog.i(RetrofitServicesManager.class.getSimpleName(), "Initialized manager with base url - "
                + retrofit.baseUrl(), false);
    }
}
