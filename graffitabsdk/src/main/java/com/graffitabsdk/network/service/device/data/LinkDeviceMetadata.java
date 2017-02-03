package com.graffitabsdk.network.service.device.data;

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
public class LinkDeviceMetadata {

    private String token;
    private final String osType = "ANDROID";
}
