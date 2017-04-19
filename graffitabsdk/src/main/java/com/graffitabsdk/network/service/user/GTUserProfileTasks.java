package com.graffitabsdk.network.service.user;

import com.graffitabsdk.model.GTExternalProvider;
import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.user.persist.GTAccountsPersistor;
import com.graffitabsdk.network.service.user.response.GTUserResponse;
import com.graffitabsdk.sdk.GTSDK;
import com.graffitabsdk.sdk.cache.GTCacheService;
import com.graffitabsdk.sdk.events.users.GTUserProfileUpdatedEvent;

import javax.inject.Inject;

/**
 * Created by georgichristov on 07/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTUserProfileTasks extends GTNetworkTask {

    private GTUserService userService;
    private GTAccountsPersistor accountsPersistor;

    @Inject
    public GTUserProfileTasks(GTCacheService gtCacheService, GTUserService userService, GTAccountsPersistor accountsPersistor) {
        super.cacheService = gtCacheService;
        this.userService = userService;
        this.accountsPersistor = accountsPersistor;
    }

    public GTRequestPerformed linkExternalProvider(String userId, String accessToken, GTExternalProvider.GTExternalProviderType type, GTResponseHandler<GTUserResponse> responseHandler) {
        GTExternalProviderMetadata externalProviderMetadata = new GTExternalProviderMetadata(userId, accessToken, type);
        GTExternalProviderData externalProviderData = new GTExternalProviderData(externalProviderMetadata);
        return performJsonRequest(userService.linkExternalProvider(externalProviderData), GTUserResponse.class, responseHandler);
    }

    public GTRequestPerformed unlinkExternalProvider(GTExternalProvider.GTExternalProviderType type, GTResponseHandler<GTUserResponse> responseHandler) {
        GTExternalProviderMetadata externalProviderMetadata = new GTExternalProviderMetadata(null, null, type);
        return performJsonRequest(userService.unlinkExternalProvider(externalProviderMetadata), GTUserResponse.class, responseHandler);
    }

    public GTRequestPerformed edit(String firstName, String lastName, String email, String about, String website, final GTResponseHandler<GTUserResponse> responseHandler) {
        GTEditProfileMetadata editProfileMetadata = new GTEditProfileMetadata(firstName, lastName, email, about, website);
        GTEditProfileData editProfileData = new GTEditProfileData(editProfileMetadata);
        return performJsonRequest(userService.edit(editProfileData), GTUserResponse.class, new GTResponseHandler<GTUserResponse>() {

            @Override
            public void onSuccess(GTResponse<GTUserResponse> gtResponse) {
                GTSDK.postEvent(new GTUserProfileUpdatedEvent(gtResponse.getObject().user));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTUserResponse> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }

    public GTRequestPerformed getMe(boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return performJsonRequest(userService.getMe(), GTUserResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getMyFullProfile(boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return performJsonRequest(userService.getMyFullProfile(), GTUserResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getUserProfile(int userId, boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return performJsonRequest(userService.getUserProfile(userId), GTUserResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getFullUserProfile(int userId, boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return performJsonRequest(userService.getFullUserProfile(userId), GTUserResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getFullUserProfileForUsername(String username, boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return performJsonRequest(userService.getFullUserProfileForUsername(username), GTUserResponse.class, responseHandler, useCache);
    }

    @Override
    protected void performExtraOperationOnSuccess(GTResponse<?> gtResponse) {
        GTUser user = ((GTUserResponse) gtResponse.getObject()).user;
        if (user.isMe())
            accountsPersistor.saveLoggedInUser(user);
    }
}
