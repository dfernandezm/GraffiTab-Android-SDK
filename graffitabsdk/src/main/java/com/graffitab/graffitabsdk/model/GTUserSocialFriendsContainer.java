package com.graffitab.graffitabsdk.model;

import java.util.List;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTUserSocialFriendsContainer {

    public GTExternalProvider.GTExternalProviderType type;
    public List<GTUser> users;
    public int resultsCount;
    public int offset;
    public int limit;

    public GTUserSocialFriendsContainer() {

    }

    public GTUserSocialFriendsContainer(GTExternalProvider.GTExternalProviderType type, List<GTUser> users, int resultsCount, int offset, int limit) {
        this.type = type;
        this.users = users;
        this.resultsCount = resultsCount;
        this.offset = offset;
        this.limit = limit;
    }
}
