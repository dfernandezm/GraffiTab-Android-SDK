package com.graffitabsdk.sdk.events.users;

import com.graffitabsdk.model.GTUser;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by georgichristov on 07/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
@Data
public class GTUserUnfollowedEvent {

    private GTUser user;
}
