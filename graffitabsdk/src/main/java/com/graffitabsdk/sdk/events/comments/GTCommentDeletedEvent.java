package com.graffitabsdk.sdk.events.comments;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by georgichristov on 06/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
@Data
public class GTCommentDeletedEvent {

    private int commentId;
    private int streamableId;
}
