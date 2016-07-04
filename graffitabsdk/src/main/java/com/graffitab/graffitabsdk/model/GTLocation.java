package com.graffitab.graffitabsdk.model;

import java.util.Date;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTLocation {

    public int id;
    public String address;
    public double latitude;
    public double longitude;
    public Date createdOn;
    public Date updatedOn;

    public GTLocation() {

    }

    public GTLocation(int id, String address, double latitude, double longitude, Date createdOn, Date updatedOn) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }
}
