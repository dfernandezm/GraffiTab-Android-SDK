package com.graffitab.graffitabsdk.model.activity;

import com.graffitab.graffitabsdk.model.GTUser;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTActivityContainer {

    public GTUser user;
    public Date date;
    public GTActivity.GTActivityType type;
    public List<GTActivity> activities;

    public GTActivityContainer() {}
}
