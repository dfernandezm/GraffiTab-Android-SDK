package com.graffitab.graffitabsdk.model.activity;

import com.graffitab.graffitabsdk.model.GTComment;
import com.graffitab.graffitabsdk.model.GTStreamable;
import com.graffitab.graffitabsdk.model.GTUser;

import java.util.Date;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTActivity {

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

    public GTActivity() {

    }

    public GTActivity(Date date, GTActivityType type, GTUser followed, GTUser follower, GTUser liker, GTStreamable likedStreamable, GTUser commenter, GTStreamable commentedStreamable, GTComment comment, GTUser creator, GTStreamable createdStreamable) {
        this.date = date;
        this.type = type;
        this.followed = followed;
        this.follower = follower;
        this.liker = liker;
        this.likedStreamable = likedStreamable;
        this.commenter = commenter;
        this.commentedStreamable = commentedStreamable;
        this.comment = comment;
        this.creator = creator;
        this.createdStreamable = createdStreamable;
    }
}
