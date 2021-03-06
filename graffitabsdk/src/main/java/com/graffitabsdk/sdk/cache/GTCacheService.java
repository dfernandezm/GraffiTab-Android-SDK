package com.graffitabsdk.sdk.cache;

import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.GTResultCode;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;

/**
 * Created by david on 27/12/2016.
 */

public class GTCacheService {

    private GTCache gtCache;

    @Inject
    public GTCacheService(GTCache gtCache) {
        this.gtCache = gtCache;
    }

    public <T> GTResponse<T> resolveRequestFromCache(String apiEndpointUrl, Class<T> type) {
        GTResponse<T> gtResponse = new GTResponse<>();
        gtResponse.setApiEndpointUrl(apiEndpointUrl);
        String cacheKey = hashApiEndpoint(apiEndpointUrl);
        T decodedResponse = gtCache.readFromCache(cacheKey, type);
        gtResponse.setObject(decodedResponse);
        gtResponse.setResultCode(GTResultCode.OK);
        gtResponse.setIsSuccessful(true);
        return gtResponse;
    }

    public <T> void saveResponseToCache(GTResponse<T> gtResponse) {
        String cacheKey = hashApiEndpoint(gtResponse.getApiEndpointUrl());
        gtCache.writeValueToCache(cacheKey, gtResponse.getObject());
    }

    public void invalidateCache() {
        gtCache.invalidateCache();
    }

    private String hashApiEndpoint(String apiEndpointUrl) {
        // Need to use it like this in Android for it to work properly
        return new String(Hex.encodeHex(DigestUtils.md5(apiEndpointUrl)));
    }
}
