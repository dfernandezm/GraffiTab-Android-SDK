package com.graffitabsdk.config.dagger.modules;

import android.app.Application;
import android.support.annotation.Nullable;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.graffitabsdk.config.GTConfig;
import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.log.GTLog;
import com.graffitabsdk.network.common.GTSharedPrefsCookiePersistor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 03/12/2016.
 */

@Module(includes = AppModule.class)
public class NetworkModule {

    private GTConfig config;

    public NetworkModule(GTConfig config) {
        this.config = config;
    }

    @Provides
    @Singleton
    CookieJar provideCookieJar(@Nullable Application application) {

        if (application == null) {
            // init cookie manager to in-memory one
           return new CookieJar() {

               private List<Cookie> cookies;

               @Override
               public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                   this.cookies =  cookies;
               }

               @Override
               public List<Cookie> loadForRequest(HttpUrl url) {
                   if (cookies != null)
                       return cookies;
                   return new ArrayList<Cookie>();

               }
           };
        } else {
            //Clearable cookieJar using Shared preferences an non-persistent cookies
            return  new PersistentCookieJar(new SetCookieCache(),
                    new GTSharedPrefsCookiePersistor(application.getBaseContext()));
        }
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit  = new Retrofit.Builder()
                  .client(okHttpClient)
                  .baseUrl(config.buildBaseApiUrl())
                  .addConverterFactory(GsonConverterFactory.create(gson))
                  .build();

        GTLog.i(this.getClass().getSimpleName(), "Initialized Retrofit with base url - "
                    + retrofit.baseUrl(), false);

        return retrofit;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat(GTApiConstants.GT_API_DATE_FORMAT)
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(CookieJar cookieJar) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .cookieJar(cookieJar).addInterceptor(httpLoggingInterceptor)
                .build();
    }
}
