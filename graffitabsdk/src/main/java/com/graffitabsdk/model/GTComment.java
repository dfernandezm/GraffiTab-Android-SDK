package com.graffitabsdk.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTComment implements Serializable {

    public int id;
    public GTStreamable streamable;
    public GTUser user;
    public String text;
    public Date createdOn;
    public Date updatedOn;

    public GTComment() {

    }
}
