package com.graffitabsdk.tasks;

import com.graffitabsdk.config.cache.GTCacheService;
import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.result.GTCommentDeletedResult;
import com.graffitabsdk.network.service.streamable.StreamableService;
import com.graffitabsdk.network.service.streamable.data.comment.CommentData;
import com.graffitabsdk.network.service.streamable.data.comment.PostCommentData;
import com.graffitabsdk.network.service.streamable.response.GTCommentResponse;
import com.graffitabsdk.network.service.streamable.response.GTListCommentsResponse;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.streamable.response.GTStreamableResponse;
import com.graffitabsdk.network.service.user.response.GTListUsersResponse;

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

    public GTRequestPerformed getComments(int streamableId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListCommentsResponse> responseHandler) {
        return performJsonRequest(streamableService.getComments(streamableId, parameters.getParameters()), GTListCommentsResponse.class, responseHandler, useCache);
    }

    public GTRequestPerformed postComment(int streamableId, String text, GTResponseHandler<GTCommentResponse> responseHandler) {
        CommentData commentData = new CommentData(text);
        PostCommentData postCommentData = new PostCommentData(commentData);
        return performJsonRequest(streamableService.postComment(streamableId, postCommentData), GTCommentResponse.class, responseHandler, false);
    }

    public GTRequestPerformed editComment(int streamableId, int commentId, String text, GTResponseHandler<GTCommentResponse> responseHandler) {
        CommentData commentData = new CommentData(text);
        PostCommentData postCommentData = new PostCommentData(commentData);
        return performJsonRequest(streamableService.editComment(streamableId, commentId, postCommentData), GTCommentResponse.class, responseHandler, false);
    }

    public GTRequestPerformed deleteComment(int streamableId, int commentId, GTResponseHandler<GTCommentDeletedResult> responseHandler) {
        return performJsonRequest(streamableService.deleteComment(streamableId, commentId), GTCommentDeletedResult.class, responseHandler, false);
    }

    public GTRequestPerformed search(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return performJsonRequest(streamableService.search(parameters.getParameters()), GTListStreamablesResponse.class, responseHandler, useCache);
    }
}
