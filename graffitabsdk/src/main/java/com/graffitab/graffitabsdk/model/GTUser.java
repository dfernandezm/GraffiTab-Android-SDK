package com.graffitab.graffitabsdk.model;

import java.util.Date;
import java.util.List;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTUser {

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

    public GTUser(int id, String guid, String username, String firstName, String lastName, String email, Date createdOn, Date updatedOn, String about, String website, GTAsset avatar, GTAsset cover, boolean followedByCurrentUser, int followersCount, int followingCount, int streamablesCount, List<GTExternalProvider> linkedAccounts) {
        this.id = id;
        this.guid = guid;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.about = about;
        this.website = website;
        this.avatar = avatar;
        this.cover = cover;
        this.followedByCurrentUser = followedByCurrentUser;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.streamablesCount = streamablesCount;
        this.linkedAccounts = linkedAccounts;
    }
}
