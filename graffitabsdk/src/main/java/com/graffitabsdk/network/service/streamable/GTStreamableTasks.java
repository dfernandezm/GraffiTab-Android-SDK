package com.graffitabsdk.network.service.streamable;

import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.result.GTActionCompleteResult;
import com.graffitabsdk.network.service.streamable.response.GTCommentResponse;
import com.graffitabsdk.network.service.streamable.response.GTListCommentsResponse;
import com.graffitabsdk.network.service.streamable.response.GTListHashtagsResponse;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.streamable.response.GTStreamableResponse;
import com.graffitabsdk.network.service.user.response.GTListUsersResponse;
import com.graffitabsdk.sdk.GTSDK;
import com.graffitabsdk.sdk.cache.GTCacheService;
import com.graffitabsdk.sdk.events.comments.GTCommentDeletedEvent;
import com.graffitabsdk.sdk.events.comments.GTCommentPostedEvent;
import com.graffitabsdk.sdk.events.comments.GTCommentUpdatedEvent;
import com.graffitabsdk.sdk.events.streamables.GTStreamableDeletedEvent;
import com.graffitabsdk.sdk.events.streamables.GTStreamableFlaggedEvent;
import com.graffitabsdk.sdk.events.streamables.GTStreamableLikedEvent;
import com.graffitabsdk.sdk.events.streamables.GTStreamableMarkedPrivateEvent;
import com.graffitabsdk.sdk.events.streamables.GTStreamableMarkedPublicEvent;
import com.graffitabsdk.sdk.events.streamables.GTStreamableUnlikedEvent;

import javax.inject.Inject;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTStreamableTasks extends GTNetworkTask {

    private StreamableService streamableService;

    @Inject
    public GTStreamableTasks(StreamableService streamableService, GTCacheService gtCacheService) {
        super.cacheService = gtCacheService;
        this.streamableService = streamableService;
    }

    public GTRequestPerformed getFeed(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(streamableService.getFeed(parameters.getParameters()), GTListStreamablesResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getPopular(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(streamableService.getPopular(parameters.getParameters()), GTListStreamablesResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getNewest(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(streamableService.getNewest(parameters.getParameters()), GTListStreamablesResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getStreamable(int streamableId, boolean useCache, GTResponseHandler<GTStreamableResponse> responseHandler) {
        return performJsonRequest(streamableService.getStreamable(streamableId), GTStreamableResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed getLikers(int streamableId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListUsersResponse> responseHandler) {
        return performJsonRequest(streamableService.getLikers(streamableId, parameters.getParameters()), GTListUsersResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed like(int streamableId, final GTResponseHandler<GTStreamableResponse> responseHandler) {
        return performJsonRequest(streamableService.like(streamableId), GTStreamableResponse.class, new GTResponseHandler<GTStreamableResponse>() {

            @Override
            public void onSuccess(GTResponse<GTStreamableResponse> gtResponse) {
                GTSDK.postEvent(new GTStreamableLikedEvent(gtResponse.getObject().streamable));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTStreamableResponse> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }

    public GTRequestPerformed unlike(int streamableId, final GTResponseHandler<GTStreamableResponse> responseHandler) {
        return performJsonRequest(streamableService.unlike(streamableId), GTStreamableResponse.class, new GTResponseHandler<GTStreamableResponse>() {

            @Override
            public void onSuccess(GTResponse<GTStreamableResponse> gtResponse) {
                GTSDK.postEvent(new GTStreamableUnlikedEvent(gtResponse.getObject().streamable));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTStreamableResponse> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }

    public GTRequestPerformed getComments(int streamableId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListCommentsResponse> responseHandler) {
        return performJsonRequest(streamableService.getComments(streamableId, parameters.getParameters()), GTListCommentsResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed postComment(int streamableId, String text, final GTResponseHandler<GTCommentResponse> responseHandler) {
        CommentData commentData = new CommentData(text);
        PostCommentData postCommentData = new PostCommentData(commentData);
        return performJsonRequest(streamableService.postComment(streamableId, postCommentData), GTCommentResponse.class, new GTResponseHandler<GTCommentResponse>() {

            @Override
            public void onSuccess(GTResponse<GTCommentResponse> gtResponse) {
                GTSDK.postEvent(new GTCommentPostedEvent(gtResponse.getObject().comment));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTCommentResponse> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }

    public GTRequestPerformed editComment(int streamableId, int commentId, String text, final GTResponseHandler<GTCommentResponse> responseHandler) {
        CommentData commentData = new CommentData(text);
        PostCommentData postCommentData = new PostCommentData(commentData);
        return performJsonRequest(streamableService.editComment(streamableId, commentId, postCommentData), GTCommentResponse.class, new GTResponseHandler<GTCommentResponse>() {

            @Override
            public void onSuccess(GTResponse<GTCommentResponse> gtResponse) {
                GTSDK.postEvent(new GTCommentUpdatedEvent(gtResponse.getObject().comment));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTCommentResponse> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }

    public GTRequestPerformed deleteComment(final int streamableId, final int commentId, final GTResponseHandler<GTActionCompleteResult> responseHandler) {
        return performJsonRequest(streamableService.deleteComment(streamableId, commentId), GTActionCompleteResult.class, new GTResponseHandler<GTActionCompleteResult>() {

            @Override
            public void onSuccess(GTResponse<GTActionCompleteResult> gtResponse) {
                GTSDK.postEvent(new GTCommentDeletedEvent(commentId, streamableId));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTActionCompleteResult> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }

    public GTRequestPerformed search(GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(streamableService.search(parameters.getParameters()), GTListStreamablesResponse.class, responseHandler);
    }

    public GTRequestPerformed searchLocation(GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(streamableService.searchLocation(parameters.getParameters()), GTListStreamablesResponse.class, responseHandler);
    }

    public GTRequestPerformed searchHashtags(GTQueryParameters parameters, GTResponseHandler<GTListHashtagsResponse> responseHandler) {
        return performJsonRequest(streamableService.searchHashtags(parameters.getParameters()), GTListHashtagsResponse.class, responseHandler);
    }

    public GTRequestPerformed makePrivate(int streamableId, final GTResponseHandler<GTStreamableResponse> responseHandler) {
        return performJsonRequest(streamableService.makePrivate(streamableId), GTStreamableResponse.class, new GTResponseHandler<GTStreamableResponse>() {

            @Override
            public void onSuccess(GTResponse<GTStreamableResponse> gtResponse) {
                GTSDK.postEvent(new GTStreamableMarkedPrivateEvent(gtResponse.getObject().streamable));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTStreamableResponse> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }

    public GTRequestPerformed makePublic(int streamableId, final GTResponseHandler<GTStreamableResponse> responseHandler) {
        return performJsonRequest(streamableService.makePublic(streamableId), GTStreamableResponse.class, new GTResponseHandler<GTStreamableResponse>() {

            @Override
            public void onSuccess(GTResponse<GTStreamableResponse> gtResponse) {
                GTSDK.postEvent(new GTStreamableMarkedPublicEvent(gtResponse.getObject().streamable));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTStreamableResponse> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }

    public GTRequestPerformed flag(int streamableId, final GTResponseHandler<GTStreamableResponse> responseHandler) {
        return performJsonRequest(streamableService.flag(streamableId), GTStreamableResponse.class, new GTResponseHandler<GTStreamableResponse>() {

            @Override
            public void onSuccess(GTResponse<GTStreamableResponse> gtResponse) {
                GTSDK.postEvent(new GTStreamableFlaggedEvent(gtResponse.getObject().streamable));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTStreamableResponse> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }

    public GTRequestPerformed delete(final int streamableId, final GTResponseHandler<GTActionCompleteResult> responseHandler) {
        return performJsonRequest(streamableService.delete(streamableId), GTActionCompleteResult.class, new GTResponseHandler<GTActionCompleteResult>() {

            @Override
            public void onSuccess(GTResponse<GTActionCompleteResult> gtResponse) {
                GTSDK.postEvent(new GTStreamableDeletedEvent(streamableId));
                responseHandler.onSuccess(gtResponse);
            }

            @Override
            public void onFailure(GTResponse<GTActionCompleteResult> gtResponse) {
                responseHandler.onFailure(gtResponse);
            }
        });
    }
}
