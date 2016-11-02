package com.graffitabsdk.model;

import java.util.List;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTListItemsResult<T> {

    public List<T> items;
    public int resultsCount;
    public int offset;
    public int limit;

    public GTListItemsResult() {}
}
