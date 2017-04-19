package com.graffitabsdk.network.service.user;

import android.graphics.Bitmap;

import com.graffitabsdk.log.GTLog;
import com.graffitabsdk.model.GTAsset;
import com.graffitabsdk.model.GTExternalProvider;
import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.assets.GTAssetService;
import com.graffitabsdk.network.service.assets.GTImagesTasks;
import com.graffitabsdk.network.service.assets.response.GTAssetResponse;
import com.graffitabsdk.network.service.user.persist.GTAccountsPersistor;
import com.graffitabsdk.sdk.GTSDK;
import com.graffitabsdk.sdk.events.users.GTUserAvatarUpdatedEvent;
import com.graffitabsdk.sdk.events.users.GTUserCoverUpdatedEvent;

import javax.inject.Inject;

/**
 * Created by david on 08/02/2017.
 */

public class GTUserImagesTasks extends GTImagesTasks {

    private GTUserService userService;
    private GTAccountsPersistor accountsPersistor;

    @Inject
    public GTUserImagesTasks(GTAssetService assetService, GTUserService userService, GTAccountsPersistor accountsPersistor) {
        super.assetService = assetService;
        this.userService = userService;
        this.accountsPersistor = accountsPersistor;
    }

    public GTRequestPerformed importAvatar(GTExternalProvider.GTExternalProviderType type, final GTResponseHandler<GTAssetResponse> responseHandler) {
        return performJsonRequest(userService.importAvatar(type),
                GTAssetResponse.class,
                new GTResponseHandler<GTAssetResponse>() {

                    @Override
                    public void onSuccess(final GTResponse<GTAssetResponse> gtResponse) {
                        // For avatar changes, we wait until the image has finished processing on the server side.
                        awaitAssetState(gtResponse.getObject().asset, new AssetProgressListener() {
                            @Override
                            public void onFinish(GTAsset asset) {
                                GTLog.i(getClass().getSimpleName(), "Finished polling for response: " + asset.state, true);

                                // Replace user avatar with the fetched one
                                gtResponse.getObject().asset = asset;
                                GTSDK.getAccountManager().getLoggedInUser().avatar = asset;

                                // Not passing in the avatar on the event, we pick it from loggedInUser
                                GTSDK.postEvent(new GTUserAvatarUpdatedEvent());
                                responseHandler.onSuccess(gtResponse);
                            }
                        });
                    }

                    @Override
                    public void onFailure(GTResponse<GTAssetResponse> gtResponse) {
                        responseHandler.onFailure(gtResponse);
                    }
                });
    }

    public GTRequestPerformed editAvatar(Bitmap image, final GTResponseHandler<GTAssetResponse> responseHandler) {
        GTEditProfileAssetsData editProfileAssetsData = GTEditProfileAssetsData.buildEditProfileAssetsData(image);
        return performJsonRequest(userService.editAvatar(editProfileAssetsData.getFileBody(), editProfileAssetsData.getName()),
                GTAssetResponse.class,
                new GTResponseHandler<GTAssetResponse>() {

                    @Override
                    public void onSuccess(final GTResponse<GTAssetResponse> gtResponse) {
                        // For avatar changes, we wait until the image has finished processing on the server side.
                        awaitAssetState(gtResponse.getObject().asset, new AssetProgressListener() {
                            @Override
                            public void onFinish(GTAsset asset) {
                                GTLog.i(getClass().getSimpleName(), "Finished polling for response: " + asset.state, true);

                                // Replace user avatar with the fetched one
                                gtResponse.getObject().asset = asset;
                                GTSDK.getAccountManager().getLoggedInUser().avatar = asset;

                                // Not passing in the avatar on the event, we pick it from loggedInUser
                                GTSDK.postEvent(new GTUserAvatarUpdatedEvent());
                                responseHandler.onSuccess(gtResponse);
                            }
                        });
                    }

                    @Override
                    public void onFailure(GTResponse<GTAssetResponse> gtResponse) {
                        responseHandler.onFailure(gtResponse);
                    }
                });
    }

    public GTRequestPerformed editCover(Bitmap image, final GTResponseHandler<GTAssetResponse> responseHandler) {
        GTEditProfileAssetsData editProfileAssetsData = GTEditProfileAssetsData.buildEditProfileAssetsData(image);
        return performJsonRequest(userService.editCover(editProfileAssetsData.getFileBody(), editProfileAssetsData.getName()),
                GTAssetResponse.class,
                new GTResponseHandler<GTAssetResponse>() {

                    @Override
                    public void onSuccess(final GTResponse<GTAssetResponse> gtResponse) {
                        // For cover changes, we wait until the image has finished processing on the server side.
                        awaitAssetState(gtResponse.getObject().asset, new AssetProgressListener() {
                            @Override
                            public void onFinish(GTAsset asset) {
                                GTLog.i(getClass().getSimpleName(), "Finished polling for response: " + asset.state, true);

                                // Replace user cover with the fetched one
                                gtResponse.getObject().asset = asset;
                                GTSDK.getAccountManager().getLoggedInUser().cover = asset;

                                // Not passing in the cover on the event, we pick it from loggedInUser
                                GTSDK.postEvent(new GTUserCoverUpdatedEvent());
                                responseHandler.onSuccess(gtResponse);
                            }
                        });
                    }

                    @Override
                    public void onFailure(GTResponse<GTAssetResponse> gtResponse) {
                        responseHandler.onFailure(gtResponse);
                    }
                });
    }

    @Override
    protected void performExtraOperationOnSuccess(GTResponse<?> gtResponse) {
        // It is going to pick the user updated by the success callback (in memory) and save it in Shared Prefs
        GTUser gtUser = GTSDK.getAccountManager().getLoggedInUser();
        accountsPersistor.saveLoggedInUser(gtUser);
    }

}
