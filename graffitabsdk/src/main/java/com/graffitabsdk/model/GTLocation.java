package com.graffitabsdk.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTLocation implements Serializable {

    public int id;
    public String address;
    public double latitude;
    public double longitude;
    public Date createdOn;
    public Date updatedOn;

    public GTLocation() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GTLocation gtLocation = (GTLocation) o;

        return id == gtLocation.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
