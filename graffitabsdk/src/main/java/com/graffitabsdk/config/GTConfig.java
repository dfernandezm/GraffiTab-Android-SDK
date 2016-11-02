package com.graffitabsdk.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by georgichristov on 04/07/16.
 */

@AllArgsConstructor
@Getter
public class GTConfig {

    public boolean logEnabled;
    public String domain;
    public boolean httpsEnabled;
    public String language;

    public static GTConfig defaultConfig() {
        return new GTDefaultConfig();
    }

    public String buildBaseApiUrl() {
        return String.format("%s://%s/api/", httpsEnabled ? "https" : "http", domain);
    }
}
