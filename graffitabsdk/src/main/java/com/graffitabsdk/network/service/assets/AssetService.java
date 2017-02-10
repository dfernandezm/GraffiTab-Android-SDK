package com.graffitabsdk.network.service.assets;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.network.service.assets.response.GTAssetResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by david on 29/12/2016.
 */

public interface AssetService {

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.ASSET_PROGRESS_ENDPOINT)
    Call<GTAssetResponse> pollForProgress(@Path("guid") String guid);
}
