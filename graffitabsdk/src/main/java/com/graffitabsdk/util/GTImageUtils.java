package com.graffitabsdk.util;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by david on 29/12/2016.
 */

public class GTImageUtils {

    public static byte[] downsampleBitmapToJpegAsByteArray(Bitmap bmp, int qualityPercent) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress( Bitmap.CompressFormat.JPEG, qualityPercent, stream);
        return stream.toByteArray();
    }
}
