package com.graffitabsdk.network.service.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by georgichristov on 02/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
@Getter
@Setter
class GTLinkDeviceMetadata {

    private String token;
    private final String osType = "ANDROID";
}
