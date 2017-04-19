package com.graffitabsdk.network.service.user;

import com.graffitabsdk.model.GTExternalProvider;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by georgichristov on 10/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
@Data
public class GTLoginExternalProviderMetadata {

    private String externalId;
    private String accessToken;
    private GTExternalProvider.GTExternalProviderType externalProviderType;
}
