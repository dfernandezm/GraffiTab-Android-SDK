package com.graffitabsdk.api;

import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.result.GTCommentDeletedResult;
import com.graffitabsdk.network.service.streamable.response.GTCommentResponse;
import com.graffitabsdk.network.service.streamable.response.GTListCommentsResponse;
import com.graffitabsdk.network.service.streamable.response.GTListHashtagsResponse;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.streamable.response.GTStreamableResponse;
import com.graffitabsdk.network.service.user.response.GTListUsersResponse;
import com.graffitabsdk.tasks.GTStreamableTasks;

import javax.inject.Inject;

/**
 * Created by georgichristov on 17/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTStreamableManager {

    private GTStreamableTasks gtStreamableTasks;

    @Inject
    public GTStreamableManager(GTStreamableTasks gtStreamableTasks) {
        this.gtStreamableTasks = gtStreamableTasks;
    }

    public GTRequestPerformed getPopular(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return gtStreamableTasks.getPopular(useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getNewest(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return gtStreamableTasks.getNewest(useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getStreamable(int streamableId, boolean useCache, GTResponseHandler<GTStreamableResponse> responseHandler) {
        return gtStreamableTasks.getStreamable(streamableId, useCache, responseHandler);
    }

    public GTRequestPerformed getLikers(int streamableId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListUsersResponse> responseHandler) {
        return gtStreamableTasks.getLikers(streamableId, useCache, parameters, responseHandler);
    }

    public GTRequestPerformed like(int streamableId, GTResponseHandler<GTStreamableResponse> responseHandler) {
        return gtStreamableTasks.like(streamableId, responseHandler);
    }

    public GTRequestPerformed unlike(int streamableId, GTResponseHandler<GTStreamableResponse> responseHandler) {
        return gtStreamableTasks.unlike(streamableId, responseHandler);
    }

    public GTRequestPerformed getComments(int streamableId, boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListCommentsResponse> responseHandler) {
        return gtStreamableTasks.getComments(streamableId, useCache, parameters, responseHandler);
    }

    public GTRequestPerformed postComment(int streamableId, String text, GTResponseHandler<GTCommentResponse> responseHandler) {
        return gtStreamableTasks.postComment(streamableId, text, responseHandler);
    }

    public GTRequestPerformed editComment(int streamableId, int commentId, String text, GTResponseHandler<GTCommentResponse> responseHandler) {
        return gtStreamableTasks.editComment(streamableId, commentId, text, responseHandler);
    }

    public GTRequestPerformed deleteComment(int streamableId, int commentId, GTResponseHandler<GTCommentDeletedResult> responseHandler) {
        return gtStreamableTasks.deleteComment(streamableId, commentId, responseHandler);
    }

    public GTRequestPerformed search(GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return gtStreamableTasks.search(parameters, responseHandler);
    }

    public GTRequestPerformed searchLocation(GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return gtStreamableTasks.searchLocation(parameters, responseHandler);
    }

    public GTRequestPerformed searchHashtags(GTQueryParameters parameters, GTResponseHandler<GTListHashtagsResponse> responseHandler) {
        return gtStreamableTasks.searchHashtags(parameters, responseHandler);
    }

    public GTRequestPerformed makePrivate(int streamableId, GTResponseHandler<GTStreamableResponse> responseHandler) {
        return gtStreamableTasks.makePrivate(streamableId, responseHandler);
    }

    public GTRequestPerformed makePublic(int streamableId, GTResponseHandler<GTStreamableResponse> responseHandler) {
        return gtStreamableTasks.makePublic(streamableId, responseHandler);
    }

    public GTRequestPerformed flag(int streamableId, GTResponseHandler<GTStreamableResponse> responseHandler) {
        return gtStreamableTasks.flag(streamableId, responseHandler);
    }
}
