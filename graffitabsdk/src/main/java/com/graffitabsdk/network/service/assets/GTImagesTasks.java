package com.graffitabsdk.network.service.assets;

import android.os.Handler;
import com.graffitabsdk.log.GTLog;
import com.graffitabsdk.model.GTAsset;
import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.assets.response.GTAssetResponse;

;import static com.graffitabsdk.constants.GTConstants.NUMBER_OF_RETRIES;
import static com.graffitabsdk.constants.GTConstants.WAIT_BETWEEN_TRIES;

/**
 * Created by david on 29/12/2016.
 */

public abstract class GTImagesTasks extends GTNetworkTask {

    protected GTAssetService assetService;

    public void awaitAssetState(final GTAsset gtAsset, final AssetProgressListener assetProgressListener) {
        awaitAssetState(gtAsset, 0, assetProgressListener);
    }

    private void awaitAssetState(final GTAsset gtAsset, final int currentNumberOfTries,
                                 final AssetProgressListener assetProgressListener) {

        Runnable awaitAssetStateRunnable = new Runnable() {
            @Override
            public void run() {
                if (gtAsset.state != GTAsset.AssetState.COMPLETED && currentNumberOfTries < NUMBER_OF_RETRIES) {
                    pollForProgress(gtAsset, new GTResponseHandler<GTAssetResponse>() {

                        @Override
                        public void onSuccess(GTResponse<GTAssetResponse> gtResponse) {
                            GTLog.i(getClass().getSimpleName(), "Polling for asset " + gtAsset.guid + ":\nState: " + gtResponse.getObject().asset.state + "\nTries: " +
                                    currentNumberOfTries, false);
                            awaitAssetState(gtResponse.getObject().asset, currentNumberOfTries + 1, assetProgressListener);
                        }

                        @Override
                        public void onFailure(GTResponse<GTAssetResponse> gtResponse) {
                            GTLog.i(getClass().getSimpleName(), "Failed to poll for asset " + gtAsset.guid + ":\nState: " + gtAsset.state + "\nTries: " +
                                    currentNumberOfTries + ":\nDetails: " + gtResponse.getResultDetail(), true);
                            awaitAssetState(gtAsset, currentNumberOfTries + 1, assetProgressListener);
                        }
                    });
                } else {
                    if (gtAsset.state == GTAsset.AssetState.COMPLETED)
                        assetProgressListener.onFinish(gtAsset);

                    if (currentNumberOfTries == NUMBER_OF_RETRIES) {
                        //TODO: we reach, maximum number of retries
                        assetProgressListener.onFinish(gtAsset);
                    }
                }
            }
        };

        runWithDelay(awaitAssetStateRunnable, WAIT_BETWEEN_TRIES);
    }

    private GTRequestPerformed pollForProgress(GTAsset asset, GTResponseHandler<GTAssetResponse> responseHandler) {
        return performJsonRequest(assetService.pollForProgress(asset.guid), GTAssetResponse.class, responseHandler);
    }

    private void runWithDelay(Runnable runnable, int delay) {
        Handler handler = new Handler();
        handler.postDelayed(runnable, delay);
    }

    protected interface AssetProgressListener {
        void onFinish(GTAsset asset);
    }
}
