package com.graffitabsdk.tasks.cache;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by david on 16/12/2016.
 */

public class GTSharedPrefsCache implements GTCache {

    private static final String API_CACHE_KEY = "API_CACHE_KEY";
    private Application application;
    private Gson gson;

    @Inject
    public GTSharedPrefsCache(Application application, Gson gson) {
        this.application = application;
        this.gson = gson;
    }

    @Override
    public <T> T readFromCache(String key, Class<T> type) {
        String json = getSharedPreferences(application).getString(key, null);
        if (json != null) {
            return gson.fromJson(json, type);
        }
        return null;
    }

    @Override
    public void invalidateCache() {
        SharedPreferences.Editor editor = getSharedPreferences(application).edit();
        editor.clear();
        editor.apply();
    }

    @Override
    public <T> void writeValueToCache(String key, T value) {
        String json = gson.toJson(value);
        SharedPreferences.Editor editor = getSharedPreferences(application).edit();
        editor.putString(key, json);
        editor.apply();
    }

    private SharedPreferences getSharedPreferences(Application application) {
        return application.getApplicationContext()
                .getSharedPreferences(API_CACHE_KEY, Context.MODE_PRIVATE);
    }
}
