package com.graffitabsdk.network.service.user.data.edit;

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
public class EditProfileMetadata {

    private String firstName;
    private String lastName;
    private String email;
    private String about;
    private String website;
}
