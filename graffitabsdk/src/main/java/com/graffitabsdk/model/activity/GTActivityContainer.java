package com.graffitabsdk.model.activity;

import com.graffitabsdk.model.GTUser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTActivityContainer implements Serializable {

    public GTUser user;
    public Date date;
    public GTActivity.GTActivityType type;
    public List<GTActivity> activities;

    public GTActivityContainer() {}
}
