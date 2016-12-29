package com.graffitabsdk.model;

import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
 * Created by georgichristov on 04/07/16.
 */

@AllArgsConstructor
public class GTAsset implements Serializable {

    public String guid;
    public String link;
    public String thumbnail;
    public String type;
    public int width;
    public int height;
    public int thumbnailWidth;
    public int thumbnailHeight;
    public AssetState state;

    public enum AssetState {
        RESIZING, PROCESSING, COMPLETED;
    }

    public GTAsset() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GTAsset gtAsset = (GTAsset) o;

        return guid.equals(gtAsset.guid);
    }

    @Override
    public int hashCode() {
        return guid.hashCode();
    }
}
