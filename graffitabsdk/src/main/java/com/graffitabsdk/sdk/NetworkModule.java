package com.graffitabsdk.sdk;

import android.app.Application;
import android.support.annotation.Nullable;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.log.GTLog;
import com.graffitabsdk.network.common.GTSharedPrefsCookiePersistor;
import com.graffitabsdk.sdk.cache.GTCache;
import com.graffitabsdk.sdk.cache.GTSharedPrefsCache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
class NetworkModule {

    private GTConfig config;

    public NetworkModule(GTConfig config) {
        this.config = config;
    }

    @Provides
    @Singleton
    CookieJar provideCookieJar(@Nullable Application application) {
        //TODO: Remove once the tests are in place (subclass module with mock)
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
                .cookieJar(cookieJar)
                .addInterceptor(new GTLanguageHeaderInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    GTCache provideCache(@Nullable  Application application, Gson gson) {
        if (application == null) {
            //TODO: change when having tests
            return new GTCache() {
                private Map<String, Object> cache = new HashMap<>();
                @Override
                public <T> T readFromCache(String key, Class<T> type) {
                    Object value = cache.get(key);

                    if (value == null) {
                        return null;
                    } else {
                        return (T) value;
                    }
                }

                @Override
                public void invalidateCache() {
                    cache.clear();
                }

                @Override
                public <T> void writeValueToCache(String key, T value) {
                    cache.put(key, value);
                }
            };
        } else {
            return new GTSharedPrefsCache(application, gson);
        }
    }
}
