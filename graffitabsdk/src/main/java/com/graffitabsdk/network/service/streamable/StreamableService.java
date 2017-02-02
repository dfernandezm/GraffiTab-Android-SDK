package com.graffitabsdk.network.service.streamable;

import com.graffitabsdk.constants.GTApiConstants;
import com.graffitabsdk.network.common.result.GTCommentDeletedResult;
import com.graffitabsdk.network.service.streamable.data.comment.PostCommentData;
import com.graffitabsdk.network.service.streamable.response.GTCommentResponse;
import com.graffitabsdk.network.service.streamable.response.GTListCommentsResponse;
import com.graffitabsdk.network.service.streamable.response.GTListHashtagsResponse;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.streamable.response.GTStreamableResponse;
import com.graffitabsdk.network.service.user.response.GTListUsersResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public interface StreamableService {

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.MY_FEED_ENDPOINT)
    Call<GTListStreamablesResponse> getFeed(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.STREAMABLES_POPULAR_ENDPOINT)
    Call<GTListStreamablesResponse> getPopular(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.STREAMABLES_NEWEST_ENDPOINT)
    Call<GTListStreamablesResponse> getNewest(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.STREAMABLE_ENDPOINT)
    Call<GTStreamableResponse> getStreamable(@Path("streamableId") int streamableId);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.STREAMABLE_LIKES_ENDPOINT)
    Call<GTListUsersResponse> getLikers(@Path("streamableId") int streamableId, @QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.STREAMABLE_LIKES_ENDPOINT)
    Call<GTStreamableResponse> like(@Path("streamableId") int streamableId);

    @Headers("Content-Type: application/json")
    @DELETE(GTApiConstants.STREAMABLE_LIKES_ENDPOINT)
    Call<GTStreamableResponse> unlike(@Path("streamableId") int streamableId);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.STREAMABLE_COMMENTS_ENDPOINT)
    Call<GTListCommentsResponse> getComments(@Path("streamableId") int streamableId, @QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @POST(GTApiConstants.STREAMABLE_COMMENTS_ENDPOINT)
    Call<GTCommentResponse> postComment(@Path("streamableId") int streamableId, @Body PostCommentData commentData);

    @Headers("Content-Type: application/json")
    @DELETE(GTApiConstants.STREAMABLE_COMMENT_ENDPOINT)
    Call<GTCommentDeletedResult> deleteComment(@Path("streamableId") int streamableId, @Path("commentId") int commentId);

    @Headers("Content-Type: application/json")
    @PUT(GTApiConstants.STREAMABLE_COMMENT_ENDPOINT)
    Call<GTCommentResponse> editComment(@Path("streamableId") int streamableId, @Path("commentId") int commentId, @Body PostCommentData commentData);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.STREAMABLES_SEARCH_HASHTAG_ENDPOINT)
    Call<GTListStreamablesResponse> search(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.STREAMABLES_SEARCH_HASHTAGS_ENDPOINT)
    Call<GTListHashtagsResponse> searchHashtags(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @GET(GTApiConstants.STREAMABLES_SEARCH_LOCATION_ENDPOINT)
    Call<GTListStreamablesResponse> searchLocation(@QueryMap Map<String, String> parameters);

    @Headers("Content-Type: application/json")
    @PUT(GTApiConstants.MY_PRIVATE_STREAMABLE_ENDPOINT)
    Call<GTStreamableResponse> makePrivate(@Path("streamableId") int streamableId);

    @Headers("Content-Type: application/json")
    @DELETE(GTApiConstants.MY_PRIVATE_STREAMABLE_ENDPOINT)
    Call<GTStreamableResponse> makePublic(@Path("streamableId") int streamableId);
}
