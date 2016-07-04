package com.graffitab.graffitabsdk.model;

import java.util.Date;

/**
 * Created by georgichristov on 04/07/16.
 */
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

    public GTStreamable(int id, GTUser user, Date createdOn, Date updatedOn, GTStreamableType type, boolean isPrivate, boolean isFlagged, GTAsset asset, double latitude, double longitude, double roll, double yaw, double pitch, boolean likedByCurrentUser, int likersCount, int commentsCount) {
        this.id = id;
        this.user = user;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.type = type;
        this.isPrivate = isPrivate;
        this.isFlagged = isFlagged;
        this.asset = asset;
        this.latitude = latitude;
        this.longitude = longitude;
        this.roll = roll;
        this.yaw = yaw;
        this.pitch = pitch;
        this.likedByCurrentUser = likedByCurrentUser;
        this.likersCount = likersCount;
        this.commentsCount = commentsCount;
    }
}
