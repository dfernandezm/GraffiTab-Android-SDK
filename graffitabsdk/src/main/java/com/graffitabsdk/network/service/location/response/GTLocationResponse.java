package com.graffitabsdk.network.service.location.response;

import com.graffitabsdk.model.GTLocation;

import java.io.Serializable;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 17/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
public class GTLocationResponse implements Serializable {

    public GTLocation location;
}
