package com.graffitabsdk.sdk.cache;

/**
 * Created by david on 16/12/2016.
 */

public interface GTCache {
    <T> T readFromCache(String key, Class<T> type);
    void invalidateCache();
    <T> void writeValueToCache(String key, T value);
}
