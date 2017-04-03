package com.graffitabsdk.network.common.result;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by georgichristov on 04/07/16.
 */
@AllArgsConstructor
public class GTListItemsResult<T> implements Serializable {

    public List<T> items;
    public int resultsCount;
    public int offset;
    public int limit;

    public GTListItemsResult() {}
}
