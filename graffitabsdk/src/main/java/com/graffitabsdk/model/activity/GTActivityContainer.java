package com.graffitabsdk.model.activity;

import com.graffitabsdk.model.GTUser;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTActivityContainer implements Serializable {

    public GTUser user;
    public Date date;
    public GTActivity.GTActivityType type;
    public List<GTActivity> activities = new ArrayList<>();

    public GTActivityContainer() {}

    public boolean isSingle() {
        return activities.size() == 1;
    }

    public boolean isEmpty() {
        return activities.size() == 0;
    }
}
