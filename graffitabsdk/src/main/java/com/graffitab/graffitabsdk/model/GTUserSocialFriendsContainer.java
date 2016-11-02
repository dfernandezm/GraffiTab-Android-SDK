package com.graffitab.graffitabsdk.model;

import java.util.List;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTUserSocialFriendsContainer {

    public GTExternalProvider.GTExternalProviderType type;
    public List<GTUser> users;
    public int resultsCount;
    public int offset;
    public int limit;

    public GTUserSocialFriendsContainer() {}
}
