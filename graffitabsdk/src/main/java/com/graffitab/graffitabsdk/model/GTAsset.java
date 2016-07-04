package com.graffitab.graffitabsdk.model;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTAsset {

    public String guid;
    public String link;
    public String thumbnail;
    public String type;
    public int width;
    public int height;
    public int thumbnailWidth;
    public int thumbnailHeight;

    public GTAsset() {

    }

    public GTAsset(String guid, String link, String thumbnail, String type, int width, int height, int thumbnailWidth, int thumbnailHeight) {
        this.guid = guid;
        this.link = link;
        this.thumbnail = thumbnail;
        this.type = type;
        this.width = width;
        this.height = height;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
    }
}
