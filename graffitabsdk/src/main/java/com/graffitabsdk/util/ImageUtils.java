package com.graffitabsdk.util;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by david on 29/12/2016.
 */

public class ImageUtils {

    public static byte[] bitmapToByteArray( Bitmap bmp ) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress( Bitmap.CompressFormat.JPEG, 60, stream );
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }

    public static byte[] downsampleBitmapToJpegAsByteArray(Bitmap bmp, int qualityPercent) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress( Bitmap.CompressFormat.JPEG, qualityPercent, stream);
        return stream.toByteArray();
    }
}
