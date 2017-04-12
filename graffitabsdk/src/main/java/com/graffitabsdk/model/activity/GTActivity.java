package com.graffitabsdk.model.activity;

import com.graffitabsdk.model.GTComment;
import com.graffitabsdk.model.GTStreamable;
import com.graffitabsdk.model.GTUser;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTActivity implements Serializable {

    public enum GTActivityType {COMMENT, CREATE_STREAMABLE, FOLLOW, LIKE};

    public Date date;
    public GTActivityType type;

    public GTUser followed;
    public GTUser follower;

    public GTUser liker;
    public GTStreamable likedStreamable;

    public GTUser commenter;
    public GTStreamable commentedStreamable;
    public GTComment comment;

    public GTUser creator;
    public GTStreamable createdStreamable;

    public GTActivity() {}

    public GTStreamable getActivityStreamable() {
        if (type == GTActivityType.COMMENT) return commentedStreamable;
        else if (type == GTActivityType.CREATE_STREAMABLE) return createdStreamable;
        else if (type == GTActivityType.LIKE) return likedStreamable;
        return null;
    }

    public GTUser getActivityUser() {
        if (type == GTActivityType.COMMENT) return commenter;
        else if (type == GTActivityType.CREATE_STREAMABLE) return creator;
        else if (type == GTActivityType.FOLLOW) return followed;
        else if (type == GTActivityType.LIKE) return liker;
        return null;
    }
}
