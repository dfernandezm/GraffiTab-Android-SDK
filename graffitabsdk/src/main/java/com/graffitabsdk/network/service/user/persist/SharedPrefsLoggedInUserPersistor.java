package com.graffitabsdk.network.service.user.persist;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.graffitabsdk.log.GTLog;
import com.graffitabsdk.model.GTUser;

import javax.inject.Inject;

/**
 * Created by david on 10/12/2016.
 */

public class SharedPrefsLoggedInUserPersistor implements LoggedInUserPersistor {

    private static final String LOGGED_IN_USER_KEY = "LOGGED_IN_USER";
    private Application application;
    private Gson gson;
    private GTUser cachedUser;

    @Inject
    public SharedPrefsLoggedInUserPersistor(Application application, Gson gson) {
        this.application = application;
        this.gson = gson;
        this.cachedUser = null;
    }

    @Override
    public GTUser getLoggedInUser() {
        if (cachedUser == null) {
            String userJson = getSharedPreferences(application).getString(LOGGED_IN_USER_KEY, null);
            if (userJson != null) {
                cachedUser = gson.fromJson(userJson, GTUser.class);
            }
        }
        return cachedUser;
    }

    @Override
    public void saveLoggedInUser(GTUser loggedInUser) {
        cachedUser = loggedInUser;
        String userJson = gson.toJson(loggedInUser);
        SharedPreferences.Editor editor = getSharedPreferences(application).edit();
        editor.putString(LOGGED_IN_USER_KEY, userJson);
        editor.apply();
    }

    @Override
    public void clearLoggedInUser() {
        cachedUser = null;
        SharedPreferences sharedPreferences = getSharedPreferences(application);
        if (sharedPreferences.contains(LOGGED_IN_USER_KEY)) {
            SharedPreferences.Editor editor = getSharedPreferences(application).edit();
            editor.remove(LOGGED_IN_USER_KEY);
            editor.apply();
            GTLog.i(getClass().getSimpleName(), "Cleared logged in user", false);
        }
    }

    private SharedPreferences getSharedPreferences(Application application) {
        return application.getApplicationContext()
                .getSharedPreferences(LOGGED_IN_USER_KEY, Context.MODE_PRIVATE);
    }
}