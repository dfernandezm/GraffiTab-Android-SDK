package com.graffitabsdk.network.service.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by georgichristov on 31/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
@Getter
@Setter
class EditPasswordData {

    private String currentPassword;
    private String newPassword;
}
