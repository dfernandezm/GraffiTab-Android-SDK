package com.graffitabsdk.config;

import android.app.Activity;
import android.app.Application;

import com.graffitabsdk.config.dagger.components.UserComponent;
import com.graffitabsdk.log.GTLog;
import com.graffitabsdk.api.GTUserManager;

/**
 * Created by david on 05/12/2016.
 */

public class GTSDK {

    private final GTConfig config;
    private UserComponent userComponent;
    private static GTSDK instance;

    private GTSDK(Application app, GTConfig config) {
        if (config == null) {
            this.config = GTConfig.defaultConfig();
        } else {
            this.config = config;
        }
        setupUserComponent(app);
    }

    public static GTUserManager getUserManager() {
        return get().userComponent.getUserManager();
    }

    public void inject(Activity activity) {
        get().userComponent.inject(activity);
    }

    public static void init(Application application, GTConfig configToSet) {
        if (instance == null) {
            instance = new GTSDK(application, configToSet);
            logConfig();
        }

    }

    public static GTConfig getConfig() {
        return get().config;
    }

    public static void setLanguage(String language) {
        get().config.language = language;
    }

    private static GTSDK get() {
        if (instance == null) {
            throw new IllegalStateException("Init not called before get()");
        }
        return instance;
    }

    private void setupUserComponent(Application app) {
        userComponent = UserComponent.Initializer.init(app, config);
    }

    private static void logConfig() {
        GTLog.i(GTSDK.class.getSimpleName(), "Setting SDK configuration...", false);
        GTLog.i(GTSDK.class.getSimpleName(), "Domain: " + instance.config.getDomain(), false);
        GTLog.i(GTSDK.class.getSimpleName(), "Log: " + instance.config.isLogEnabled(), false);
        GTLog.i(GTSDK.class.getSimpleName(), "HTTPS: " + instance.config.isHttpsEnabled(), false);
        GTLog.i(GTSDK.class.getSimpleName(), "API url: " + instance.config.buildBaseApiUrl(), false);
    }
}
