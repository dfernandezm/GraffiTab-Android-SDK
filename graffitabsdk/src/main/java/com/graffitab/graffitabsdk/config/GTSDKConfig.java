package com.graffitab.graffitabsdk.config;

import com.graffitab.graffitabsdk.log.GTLog;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTSDKConfig {

    public static GTSDKConfig sharedInstance = new GTSDKConfig();

    private GTConfig config;

    public GTConfig getConfig() {
        if (this.config == null) {
            setConfig(GTConfig.defaultConfig());
        }

        return config;
    }

    public void setConfig(GTConfig config) {
        if (config == null)
            config = GTConfig.defaultConfig();

        this.config = config;

        GTLog.i(getClass().getSimpleName(), "Set SDK configuration", false);
        GTLog.i(getClass().getSimpleName(), "Domain: " + config.getDomain(), false);
        GTLog.i(getClass().getSimpleName(), "Log: " + config.logEnabled, false);
        GTLog.i(getClass().getSimpleName(), "HTTPS: " + config.isHttpsEnabled(), false);
        GTLog.i(getClass().getSimpleName(), "API url: " + config.buildApiUrl(), false);
    }
}
