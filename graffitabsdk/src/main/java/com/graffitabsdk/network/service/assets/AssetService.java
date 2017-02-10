package com.graffitabsdk.network.service.assets;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.network.service.assets.response.GTAssetResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by david on 29/12/2016.
 */

public interface AssetService {

    @Multipart
    @POST(GTApiConstants.MY_AVATAR)
    Call<GTAssetResponse> uploadAvatar(@Part MultipartBody.Part file, @Part("name") RequestBody name);

    @Multipart
    @POST(GTApiConstants.MY_COVER)
    Call<GTAssetResponse> uploadCover(@Part MultipartBody.Part file, @Part("name") RequestBody name);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.ASSET_PROGRESS_ENDPOINT)
    Call<GTAssetResponse> pollForProgress(@Path("guid") String guid);
}
