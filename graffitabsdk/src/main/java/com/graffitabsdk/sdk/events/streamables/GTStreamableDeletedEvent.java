package com.graffitabsdk.sdk.events.streamables;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by georgichristov on 06/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
@Data
public class GTStreamableDeletedEvent {

    private int streamableId;
}
