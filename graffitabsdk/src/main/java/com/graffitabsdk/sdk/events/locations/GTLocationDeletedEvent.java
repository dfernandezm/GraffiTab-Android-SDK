package com.graffitabsdk.sdk.events.locations;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by georgichristov on 06/02/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
@Data
public class GTLocationDeletedEvent {

    private int locationId;
}
