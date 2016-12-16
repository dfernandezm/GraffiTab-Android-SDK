package com.graffitabsdk.tasks.cache;

import com.graffitabsdk.network.common.GTResponse;
import com.graffitabsdk.network.common.ResultCode;
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

    public <T> GTResponse<T> resolveRequestFromCache(String apiEndpointUrl) {
        GTResponse<T> gtResponse = new GTResponse<>();
        gtResponse.setApiEndpointUrl(apiEndpointUrl);
        String cacheKey = hashApiEndpoint(apiEndpointUrl);
        T decodedResponse = gtCache.readFromCache(cacheKey);
        gtResponse.setObject(decodedResponse);
        gtResponse.setResultCode(ResultCode.OK);
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
        // In older versions of Android there were some problems with this, replace with
        // 'return new String(Hex.encodeHex(DigestUtils.md5(apiEndpointUrl)))' if this way does not work
        return DigestUtils.md5Hex(apiEndpointUrl);
    }
}
