package com.graffitabsdk.model;

import java.util.Date;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTNotification {

    public enum GTNotificationType {COMMENT, FOLLOW, LIKE, MENTION, WELCOME}

    public boolean isRead;
    public Date date;
    public GTNotificationType type;
    public GTUser follower;
    public GTUser liker;
    public GTStreamable likedStreamable;
    public GTUser commenter;
    public GTStreamable commentedStreamable;
    public GTComment comment;
    public GTUser mentioner;
    public GTStreamable mentionedStreamable;
    public GTComment mentionedComment;

    public GTNotification() {

    }
}
