package com.graffitabsdk.network.service.user;

import com.graffitabsdk.model.GTExternalProvider;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by georgichristov on 13/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
@Data
public class GTExternalProviderMetadata {

    private String userId;
    private String accessToken;
    private GTExternalProvider.GTExternalProviderType type;
}
