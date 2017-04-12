package com.graffitabsdk.model.activity;

import com.graffitabsdk.model.GTStreamable;
import com.graffitabsdk.model.GTUser;

import java.io.Serializable;
import java.util.ArrayList;
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
    public List<GTActivity> activities = new ArrayList<>();

    public GTActivityContainer() {}

    public boolean isSingle() {
        return activities.size() == 1;
    }

    public boolean isEmpty() {
        return activities.size() == 0;
    }

    public GTActivity getFirst() {
        return activities.get(0);
    }

    public List<GTStreamable> getStreamables() {
        List<GTStreamable> streamables = new ArrayList<>();
        for (GTActivity activity: activities)
            streamables.add(activity.getActivityStreamable());
        return streamables;
    }

    public List<GTUser> getUsers() {
        List<GTUser> users = new ArrayList<>();
        for (GTActivity activity: activities) {
            users.add(activity.getActivityUser());
        }
        return users;
    }
}
