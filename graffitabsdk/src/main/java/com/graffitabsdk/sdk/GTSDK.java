package com.graffitabsdk.sdk;

import android.app.Activity;
import android.app.Application;

import com.graffitabsdk.api.GTAccountManager;
import com.graffitabsdk.api.GTMeManager;
import com.graffitabsdk.api.GTStreamableManager;
import com.graffitabsdk.api.GTUserManager;
import com.graffitabsdk.log.GTLog;
import com.squareup.otto.Bus;

import java.lang.ref.WeakReference;

/**
 * Created by david on 05/12/2016.
 */

public class GTSDK {

    private final GTConfig config;
    private GTComponents gtComponent;
    private static GTSDK instance;
    private WeakReference<Application> application;
    private Bus eventBus;

    private GTSDK(Application app, GTConfig config) {
        this.application = new WeakReference<>(app);
        this.eventBus = new Bus();

        if (config == null) {
            this.config = GTConfig.defaultConfig();
        } else {
            this.config = config;
        }
        setupComponents(app);
    }

    public static void registerEventListener(Object listener) {
        get().eventBus.register(listener);
        GTLog.i(get().getClass().getSimpleName(), "Registered event bus for " + listener, false);
    }

    public static void unregisterEventListener(Object listener) {
        try {
            get().eventBus.unregister(listener);
            GTLog.i(get().getClass().getSimpleName(), "Unregistered event bus for " + listener, false);
        } catch (IllegalArgumentException e) {
            GTLog.e(get().getClass().getSimpleName(), "Failed to unregister listener: " + e.getMessage(), false);
        }
    }

    public static void postEvent(Object event) {
        get().eventBus.post(event);
    }

    public static Application getApplication() {
        return get().application.get();
    }

    public static GTStreamableManager getStreamableManager() {
        return get().gtComponent.getStreamableManager();
    }

    public static GTUserManager getUserManager() {
        return get().gtComponent.getUserManager();
    }

    public static GTMeManager getMeManager() {
        return get().gtComponent.getMeManager();
    }

    public static GTAccountManager getAccountManager() {
        return get().gtComponent.getAccountManager();
    }

    public static void invalidateCache() {
        get().gtComponent.getCacheService().invalidateCache();
    }

    public void inject(Activity activity) {
        get().gtComponent.inject(activity);
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

    private void setupComponents(Application app) {
        gtComponent = GTComponents.Initializer.init(app, config);
    }

    private static void logConfig() {
        GTLog.i(GTSDK.class.getSimpleName(), "Setting SDK configuration...", false);
        GTLog.i(GTSDK.class.getSimpleName(), "Domain: " + instance.config.getDomain(), false);
        GTLog.i(GTSDK.class.getSimpleName(), "Log: " + instance.config.isLogEnabled(), false);
        GTLog.i(GTSDK.class.getSimpleName(), "HTTPS: " + instance.config.isHttpsEnabled(), false);
        GTLog.i(GTSDK.class.getSimpleName(), "API url: " + instance.config.buildBaseApiUrl(), false);
    }
}
