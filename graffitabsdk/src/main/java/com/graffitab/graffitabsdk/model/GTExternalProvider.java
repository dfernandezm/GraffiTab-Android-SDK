package com.graffitab.graffitabsdk.model;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTExternalProvider {

    public enum GTExternalProviderType {FACEBOOK, TWITTER, GOOGLE}

    public String userId;
    public GTExternalProviderType type;

    public GTExternalProvider() {

    }

    public GTExternalProvider(String userId, GTExternalProviderType type) {
        this.userId = userId;
        this.type = type;
    }
}
