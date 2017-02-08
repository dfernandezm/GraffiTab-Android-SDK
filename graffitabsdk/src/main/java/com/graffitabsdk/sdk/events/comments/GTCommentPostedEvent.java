package com.graffitabsdk.sdk.events.comments;

import com.graffitabsdk.model.GTComment;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by georgichristov on 06/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
@Data
public class GTCommentPostedEvent {

    private GTComment comment;
}
