package com.graffitabsdk.network.service.streamable.response;

import com.graffitabsdk.model.GTComment;

import java.io.Serializable;

import lombok.AllArgsConstructor;

/**
 * Created by georgichristov on 29/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
public class GTCommentResponse implements Serializable {

    public GTComment comment;
}
