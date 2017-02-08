package com.graffitabsdk.model;

import com.graffitabsdk.sdk.GTSDK;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTStreamable implements Serializable {

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

    public GTStreamable() {}

    public boolean isMine() {
        GTUser me = GTSDK.getAccountManager().getLoggedInUser();
        if (me != null)
            return me.equals(user);
        return false;
    }

    public void addToLikersCount() {
        likersCount++;
    }

    public void removeFromLikersCount() {
        likersCount--;
        if (likersCount < 0) likersCount = 0;
    }

    public void addToCommentsCount() {
        commentsCount++;
    }

    public void removeFromCommentsCount() {
        commentsCount--;
        if (commentsCount < 0) commentsCount = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GTStreamable gtStreamable = (GTStreamable) o;

        return id == gtStreamable.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
