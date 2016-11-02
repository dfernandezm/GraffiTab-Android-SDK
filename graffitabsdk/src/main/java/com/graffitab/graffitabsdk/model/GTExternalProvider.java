package com.graffitab.graffitabsdk.model;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTExternalProvider {

    public enum GTExternalProviderType {FACEBOOK, TWITTER, GOOGLE}

    public String userId;
    public GTExternalProviderType type;

    public GTExternalProvider() {
    }
}
