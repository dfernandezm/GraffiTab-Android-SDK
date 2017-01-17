package com.graffitabsdk.network.common.result;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;

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
