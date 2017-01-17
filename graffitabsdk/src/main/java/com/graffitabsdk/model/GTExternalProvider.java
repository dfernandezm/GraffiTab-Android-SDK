package com.graffitabsdk.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTExternalProvider implements Serializable {

    public enum GTExternalProviderType {FACEBOOK, TWITTER, GOOGLE}

    public String userId;
    public GTExternalProviderType type;

    public GTExternalProvider() {
    }
}
