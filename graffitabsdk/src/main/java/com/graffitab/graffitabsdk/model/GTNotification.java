package com.graffitab.graffitabsdk.model;

import java.util.Date;

/**
 * Created by georgichristov on 04/07/16.
 */
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

    public GTNotification(boolean isRead, Date date, GTNotificationType type, GTUser follower, GTUser liker, GTStreamable likedStreamable, GTUser commenter, GTStreamable commentedStreamable, GTComment comment, GTUser mentioner, GTStreamable mentionedStreamable, GTComment mentionedComment) {
        this.isRead = isRead;
        this.date = date;
        this.type = type;
        this.follower = follower;
        this.liker = liker;
        this.likedStreamable = likedStreamable;
        this.commenter = commenter;
        this.commentedStreamable = commentedStreamable;
        this.comment = comment;
        this.mentioner = mentioner;
        this.mentionedStreamable = mentionedStreamable;
        this.mentionedComment = mentionedComment;
    }
}
