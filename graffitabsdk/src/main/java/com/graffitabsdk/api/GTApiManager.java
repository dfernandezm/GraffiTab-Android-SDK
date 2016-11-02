package com.graffitabsdk.api;

import javax.inject.Inject;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import okhttp3.CookieJar;


public class GTApiManager {

    private CookieJar cookieJar;

    @Inject
    public GTApiManager(CookieJar cookieJar) {
        this.cookieJar = cookieJar;
    }

    public void clearCookies() {
        if (cookieJar instanceof ClearableCookieJar) {
             ((ClearableCookieJar) cookieJar).clear();
        }
    }
}
