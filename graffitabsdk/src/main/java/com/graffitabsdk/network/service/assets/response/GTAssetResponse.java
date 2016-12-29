package com.graffitabsdk.network.service.assets.response;

import com.graffitabsdk.model.GTAsset;
import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
 * Created by david on 02/02/2017.
 */
@AllArgsConstructor
public class GTAssetResponse implements Serializable {

    public GTAsset asset;
}