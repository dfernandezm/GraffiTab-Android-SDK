package com.graffitabsdk.model;

import java.util.Date;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTStreamable {

    public enum GTStreamableType {GRAFFITI}

    public int id;
    public GTUser user;
    public Date createdOn;
    public Date updatedOn;
    public GTStreamableType type;
    public boolean isPrivate;
    public boolean isFlagged;
    public GTAsset asset;
    public double latitude;
    public double longitude;
    public double roll;
    public double yaw;
    public double pitch;
    public boolean likedByCurrentUser;
    public int likersCount;
    public int commentsCount;

    public GTStreamable() {

    }
}
