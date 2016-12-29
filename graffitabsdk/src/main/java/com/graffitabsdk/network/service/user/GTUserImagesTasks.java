package com.graffitabsdk.network.service.user;

import android.graphics.Bitmap;
import com.graffitabsdk.log.GTLog;
import com.graffitabsdk.model.GTAsset;
import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.assets.AssetService;
import com.graffitabsdk.network.service.assets.GTImagesTasks;
import com.graffitabsdk.network.service.assets.UploadRequest;
import com.graffitabsdk.network.service.assets.response.GTAssetResponse;
import com.graffitabsdk.network.service.user.persist.LoggedInUserPersistor;
import com.graffitabsdk.sdk.GTSDK;
import com.graffitabsdk.sdk.events.users.GTUserAvatarUpdatedEvent;
import com.graffitabsdk.util.UploadUtils;

import javax.inject.Inject;

/**
 * Created by david on 08/02/2017.
 */

public class GTUserImagesTasks extends GTImagesTasks {

    private LoggedInUserPersistor loggedInUserPersistor;

    @Inject
    public GTUserImagesTasks(AssetService assetService, LoggedInUserPersistor loggedInUserPersistor) {
        super.assetService = assetService;
        this.loggedInUserPersistor = loggedInUserPersistor;
    }

    public GTRequestPerformed uploadAvatar(Bitmap image, final GTResponseHandler<GTAssetResponse> responseHandler) {
        UploadRequest uploadRequest = UploadUtils.prepareImageUploadRequest(image);
        return performJsonRequest(assetService.uploadAvatar(uploadRequest.getFileBody(), uploadRequest.getName()),
                GTAssetResponse.class,
                new GTResponseHandler<GTAssetResponse>() {
                    @Override
                    public void onSuccess(final GTResponse<GTAssetResponse> gtResponse) {
                        awaitAssetState(gtResponse.getObject().asset, 0, new AssetProgressListener() {
                            @Override
                            public void onFinish(GTAsset asset) {
                                GTLog.i(getClass().getSimpleName(), "Finished polling for response: " + asset.state, true);
                                // Replace asset with the fetched one
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

    @Override
    protected void performExtraOperationOnSuccess(GTResponse<?> gtResponse) {
        // It is going to pick the one updated by the success callback (in memory) and save it in Shared Prefs
        GTUser gtUser = GTSDK.getAccountManager().getLoggedInUser();
        loggedInUserPersistor.saveLoggedInUser(gtUser);
    }

}
