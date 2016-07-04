package com.graffitab.graffitabsdk.model.activity;

import com.graffitab.graffitabsdk.model.GTUser;

import java.util.Date;
import java.util.List;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTActivityContainer {

    public GTUser user;
    public Date date;
    public GTActivity.GTActivityType type;
    public List<GTActivity> activities;

    public GTActivityContainer() {

    }

    public GTActivityContainer(GTUser user, Date date, GTActivity.GTActivityType type, List<GTActivity> activities) {
        this.user = user;
        this.date = date;
        this.type = type;
        this.activities = activities;
    }
}
