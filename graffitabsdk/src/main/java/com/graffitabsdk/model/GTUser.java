package com.graffitabsdk.model;

import com.graffitabsdk.sdk.GTSDK;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTUser implements Serializable {

    public int id;
    public String guid;
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public Date createdOn;
    public Date updatedOn;
    public String about;
    public String website;
    public GTAsset avatar;
    public GTAsset cover;
    public boolean followedByCurrentUser;
    public int followersCount;
    public int followingCount;
    public int streamablesCount;
    public List<GTExternalProvider> linkedAccounts;

    public GTUser() {

    }

    public String fullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public String mentionUsername() {
        return String.format("@%s", username);
    }

    public String streamablesCountAsString() {
        return itemsCountAsString(streamablesCount);
    }

    public String followersCountAsString() {
        return itemsCountAsString(followersCount);
    }

    public String followingCountAsString() {
        return itemsCountAsString(followingCount);
    }

    public String aboutString() {
        String text = "";
        if (about != null) text += about;
        if (website != null) text += (about != null ? "\n\n" : "") + website;

        return text;
    }

    public boolean hasAvatar() {
        return avatar != null && avatar.thumbnail != null && avatar.link != null;
    }

    public boolean hasCover() {
        return cover != null && cover.thumbnail != null && cover.link != null;
    }

    public boolean isMe() {
        GTUser me = GTSDK.getAccountManager().getLoggedInUser();
        if (me != null)
            return me.equals(this);
        return false;
    }

    private String itemsCountAsString(int count) {
        if (count < 10000)
            return count + "";
        return String.format("%dK", count / 1000);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GTUser gtUser = (GTUser) o;

        return id == gtUser.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
