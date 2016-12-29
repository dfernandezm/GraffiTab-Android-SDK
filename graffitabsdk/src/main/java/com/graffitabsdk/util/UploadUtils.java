package com.graffitabsdk.util;

import android.graphics.Bitmap;
import com.graffitabsdk.network.service.assets.UploadRequest;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by david on 29/12/2016.
 */

public class UploadUtils {

    // This is the name of the File part in the Multipart Request payload (this name is required server side)
    private static String MULTIPART_FILE_PART_NAME = "file";

    public static UploadRequest prepareImageUploadRequest(Bitmap image) {

        byte[] imageAsByteArray = ImageUtils.downsampleBitmapToJpegAsByteArray(image, 60);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/jpeg"), imageAsByteArray);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData(MULTIPART_FILE_PART_NAME, "file", reqFile);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "imageUpload");

        UploadRequest uploadRequest = new UploadRequest();
        uploadRequest.setFileBody(fileBody);
        uploadRequest.setName(name);

        return uploadRequest;
    }
}
