package com.graffitabsdk.network.service.streamable.response;

import com.graffitabsdk.model.GTStreamable;

import java.io.Serializable;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 17/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
public class GTStreamableResponse implements Serializable {

    public GTStreamable streamable;
}
