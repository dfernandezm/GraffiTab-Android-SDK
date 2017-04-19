package com.graffitabsdk.network.service.user;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
@Data
class GTRegisterMetadata {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
