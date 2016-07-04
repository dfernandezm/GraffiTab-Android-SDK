package com.graffitab.graffitabsdk.model;

import java.util.List;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTListItemsResult<T> {

    public List<T> items;
    public int resultsCount;
    public int offset;
    public int limit;

    public GTListItemsResult() {

    }

    public GTListItemsResult(List<T> items, int resultsCount, int offset, int limit) {
        this.items = items;
        this.resultsCount = resultsCount;
        this.offset = offset;
        this.limit = limit;
    }
}
