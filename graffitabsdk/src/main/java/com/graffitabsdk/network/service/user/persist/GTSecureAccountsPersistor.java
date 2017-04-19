package com.graffitabsdk.network.service.user.persist;

import android.app.Application;

import com.google.gson.Gson;
import com.graffitabsdk.log.GTLog;
import com.graffitabsdk.model.GTUser;
import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;

/**
 * Created by david on 10/12/2016.
 */

public class GTSecureAccountsPersistor implements GTAccountsPersistor {

    private static final String SECURE_CURRENT_USER = "SECURE_CURRENT_USER";
    private static final String SECURE_LAST_USER = "SECURE_LAST_USER";
    private static final String SECURE_LAST_PASSWORD = "SECURE_LAST_PASSWORD";

    private Application application;
    private Gson gson;
    private GTUser cachedUser;

    @Inject
    public GTSecureAccountsPersistor(Application application, Gson gson) {
        this.application = application;
        this.gson = gson;
        this.cachedUser = null;

        Hawk.init(application).build();
    }

    @Override
    public GTUser getLoggedInUser() {
        if (cachedUser == null) {
            if (Hawk.contains(SECURE_CURRENT_USER)) {
                String userJson = Hawk.get(SECURE_CURRENT_USER);
                cachedUser = gson.fromJson(userJson, GTUser.class);
            }
        }
        return cachedUser;
    }

    @Override
    public void saveLoggedInUser(GTUser loggedInUser) {
        cachedUser = loggedInUser;
        Hawk.put(SECURE_CURRENT_USER, gson.toJson(loggedInUser));

        // Update last logged in user.
        saveLastLoggedInUser(loggedInUser);
    }

    @Override
    public void clearLoggedInUser() {
        cachedUser = null;
        Hawk.delete(SECURE_CURRENT_USER);
        GTLog.i(getClass().getSimpleName(), "Cleared logged in user", false);
    }

    @Override
    public GTUser getLastLoggedInUser() {
        if (Hawk.contains(SECURE_LAST_USER)) {
            String userJson = Hawk.get(SECURE_LAST_USER);
            return gson.fromJson(userJson, GTUser.class);
        }
        return null;
    }

    @Override
    public String getLastLoggedInUserPassword() {
        if (Hawk.contains(SECURE_LAST_PASSWORD))
            return Hawk.get(SECURE_LAST_PASSWORD);
        return null;
    }

    @Override
    public void saveLastLoggedInUser(GTUser user) {
        saveLastLoggedInUser(user, getLastLoggedInUserPassword());
    }

    @Override
    public void saveLastLoggedInUser(GTUser user, String password) {
        Hawk.put(SECURE_LAST_USER, gson.toJson(user));
        Hawk.put(SECURE_LAST_PASSWORD, password);
    }

    @Override
    public void clearLastLoggedInUser() {
        Hawk.delete(SECURE_LAST_USER);
        Hawk.delete(SECURE_LAST_PASSWORD);
    }
}
