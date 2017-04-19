package com.graffitabsdk.network.service.user;

import android.graphics.Bitmap;

import com.graffitabsdk.util.GTImageUtils;

import lombok.Data;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.graffitabsdk.constants.GTConstants.IMAGE_COMPRESSION_QUALITY;

/**
 * Created by david on 29/12/2016.
 */

@Data
class GTEditProfileAssetsData {

    // This is the name of the File part in the Multipart Request payload (this name is required server side)
    private static String MULTIPART_FILE_PART_NAME = "file";

    private RequestBody name;
    private MultipartBody.Part fileBody;

    public static GTEditProfileAssetsData buildEditProfileAssetsData(Bitmap image) {
        // Compress image.
        byte[] imageAsByteArray = GTImageUtils.downsampleBitmapToJpegAsByteArray(image, IMAGE_COMPRESSION_QUALITY);

        // Build multipart file.
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/jpeg"), imageAsByteArray);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData(MULTIPART_FILE_PART_NAME, "file", reqFile);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "imageUpload");

        GTEditProfileAssetsData editProfileAssetsData = new GTEditProfileAssetsData();
        editProfileAssetsData.setFileBody(fileBody);
        editProfileAssetsData.setName(name);
        return editProfileAssetsData;
    }
}
