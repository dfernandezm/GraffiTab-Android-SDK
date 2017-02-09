package com.graffitabsdk.sdk.events.streamables;

import com.graffitabsdk.model.GTStreamable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by georgichristov on 06/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
@Data
public class GTStreamableCreatedEvent {

    private GTStreamable streamable;
}
