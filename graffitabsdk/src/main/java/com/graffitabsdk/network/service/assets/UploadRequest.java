package com.graffitabsdk.network.service.assets;

import lombok.Getter;
import lombok.Setter;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by david on 29/12/2016.
 */

@Getter
@Setter
public class UploadRequest {
    private RequestBody name;
    private MultipartBody.Part fileBody;
}
