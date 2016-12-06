package com.graffitabsdk.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
@ToString
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
}
