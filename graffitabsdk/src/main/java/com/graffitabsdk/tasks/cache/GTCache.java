package com.graffitabsdk.tasks.cache;

/**
 * Created by david on 16/12/2016.
 */

public interface GTCache {
    <T> T readFromCache(String key);
    void invalidateCache();
    <T> void writeValueToCache(String key, T value);
}
