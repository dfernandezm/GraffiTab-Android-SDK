package com.graffitabsdk.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;

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

    public GTAsset() {}
}
