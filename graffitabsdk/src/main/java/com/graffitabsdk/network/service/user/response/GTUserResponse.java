package com.graffitabsdk.network.service.user.response;

import com.graffitabsdk.model.GTUser;

import java.io.Serializable;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 17/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
public class GTUserResponse implements Serializable {

    public GTUser user;
}
