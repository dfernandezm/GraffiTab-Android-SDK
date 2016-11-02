package com.graffitab.graffitabsdk.config;

import com.graffitab.graffitabsdk.log.GTLog;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTSDKConfig {

    private static GTConfig config;

    public static GTConfig get() {
        if (config == null) {
            set(GTConfig.defaultConfig());
        }
        return config;
    }

    public static void set(GTConfig configToSet) {

        if (configToSet == null) {
            config = GTConfig.defaultConfig();
        }

        config = configToSet;

        GTLog.i(GTSDKConfig.class.getSimpleName(), "Setting SDK configuration...", false);
        GTLog.i(GTSDKConfig.class.getSimpleName(), "Domain: " + config.getDomain(), false);
        GTLog.i(GTSDKConfig.class.getSimpleName(), "Log: " + config.isLogEnabled(), false);
        GTLog.i(GTSDKConfig.class.getSimpleName(), "HTTPS: " + config.isHttpsEnabled(), false);
        GTLog.i(GTSDKConfig.class.getSimpleName(), "API url: " + config.buildBaseApiUrl(), false);
    }
}
