package com.graffitab.graffitabsdk.config;

import com.graffitab.graffitabsdk.constants.GTConstants;

/**
 * Created by georgichristov on 04/07/16.
 */
class GTDefaultConfig extends GTConfig {
    GTDefaultConfig() {
        super(false, GTConstants.GT_API_DOMAIN, true, "en");
    }
}
