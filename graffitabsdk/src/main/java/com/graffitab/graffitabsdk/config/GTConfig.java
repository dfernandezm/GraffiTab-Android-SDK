package com.graffitab.graffitabsdk.config;

import com.graffitab.graffitabsdk.log.GTLog;

import lombok.Getter;

/**
 * Created by georgichristov on 04/07/16.
 */

@Getter
public class GTConfig {

    public boolean logEnabled;

    private String domain;
    private boolean httpsEnabled;

    public static GTConfig defaultConfig() {
        return new GTDefaultConfig();
    }

    public GTConfig(String domain, boolean logEnabled, boolean httpsEnabled) {
        this.domain = domain;
        this.logEnabled = logEnabled;
        this.httpsEnabled = httpsEnabled;
    }

    public String buildApiUrl() {
        return String.format("%s://%s/api", httpsEnabled ? "https" : "http", domain);
    }
}
