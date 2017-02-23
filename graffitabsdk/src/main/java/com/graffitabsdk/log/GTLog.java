package com.graffitabsdk.log;

import android.util.Log;

import com.graffitabsdk.sdk.GTConfig;
import com.graffitabsdk.sdk.GTSDK;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTLog {
    private static GTLog ourInstance = new GTLog();

    public static GTLog get() {
        return ourInstance;
    }

    public static void i(String tag, String message, boolean forceLog) {
        if (forceLog || canLog())
            Log.i(tag, message);
    }

    public static void i(String tag, String message, Throwable throwable, boolean forceLog) {
        if (forceLog || canLog())
            Log.i(tag, message, throwable);
    }

    public static void v(String tag, String message, boolean forceLog) {
        if (forceLog || canLog())
            Log.v(tag, message);
    }

    public static void v(String tag, String message, Throwable throwable, boolean forceLog) {
        if (forceLog || canLog())
            Log.v(tag, message, throwable);
    }

    public static void d(String tag, String message, boolean forceLog) {
        if (forceLog || canLog())
            Log.d(tag, message);
    }

    public static void d(String tag, String message, Throwable throwable, boolean forceLog) {
        if (forceLog || canLog())
            Log.d(tag, message, throwable);
    }

    public static void e(String tag, String message, boolean forceLog) {
        if (forceLog || canLog())
            Log.e(tag, message);
    }

    public static void e(String tag, String message, Throwable throwable, boolean forceLog) {
        if (forceLog || canLog())
            Log.e(tag, message, throwable);
    }

    private static boolean canLog() {
        // GTSDK.init() should have been called first
        GTConfig config = GTSDK.getConfig();
        return config.isLogEnabled();
    }
}
