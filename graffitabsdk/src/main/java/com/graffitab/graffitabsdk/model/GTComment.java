package com.graffitab.graffitabsdk.model;

import java.util.Date;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTComment {

    public int id;
    public GTStreamable streamable;
    public GTUser user;
    public String text;
    public Date createdOn;
    public Date updatedOn;

    public GTComment() {

    }

    public GTComment(int id, GTStreamable streamable, GTUser user, String text, Date createdOn, Date updatedOn) {
        this.id = id;
        this.streamable = streamable;
        this.user = user;
        this.text = text;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }
}
