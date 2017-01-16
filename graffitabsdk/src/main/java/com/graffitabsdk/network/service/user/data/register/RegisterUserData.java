package com.graffitabsdk.network.service.user.data.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
@AllArgsConstructor
@Getter
@Setter
public class RegisterUserData {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
