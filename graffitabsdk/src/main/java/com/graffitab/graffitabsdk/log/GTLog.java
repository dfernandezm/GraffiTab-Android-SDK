package com.graffitab.graffitabsdk.log;

import android.util.Log;

import com.graffitab.graffitabsdk.config.GTConfig;
import com.graffitab.graffitabsdk.config.GTSDKConfig;

/**
 * Created by georgichristov on 04/07/16.
 */
public class GTLog {
    private static GTLog ourInstance = new GTLog();

    public static GTLog getInstance() {
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

    private static boolean canLog() {
        GTConfig config = GTSDKConfig.sharedInstance.getConfig();
        return config.isLogEnabled();
    }
}
