package com.graffitabsdk.network.common.params;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by georgichristov on 17/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTQueryParameters {

    protected Map<String,String> params = new HashMap<>();

    public enum GTParameterType {OFFSET, LIMIT, QUERY, LATITUDE, LONGITUDE, RADIUS};

    public void addParameter(GTParameterType parameter, String value) {
        params.put(parameter.name().toLowerCase(), value);
    }

    public void addParameter(GTParameterType parameter, int value) {
        params.put(parameter.name().toLowerCase(), value + "");
    }

    public Map<String,String> getParameters() {
        return params;
    }
}
