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

    public enum GTParameterType {
        offset, limit, query, latitude, longitude, radius, numberOfItemsInGroup
    };

    public GTQueryParameters addParameter(GTParameterType parameter, String value) {
        params.put(parameter.name(), value);
        return this;
    }

    public GTQueryParameters addParameter(GTParameterType parameter, int value) {
        params.put(parameter.name(), value + "");
        return this;
    }

    public Map<String,String> getParameters() {
        return params;
    }
}
