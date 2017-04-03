package com.graffitabsdk.model;

import com.graffitabsdk.network.common.result.GTListItemsResult;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTUserSocialFriendsContainer extends GTListItemsResult<GTUser> {

    public GTExternalProvider.GTExternalProviderType type;
    public List<GTUser> users;

    public GTUserSocialFriendsContainer() {}
}
