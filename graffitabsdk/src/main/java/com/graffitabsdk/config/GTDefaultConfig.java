package com.graffitabsdk.config;

import com.graffitabsdk.constants.GTApiConstants;

/**
 * Created by georgichristov on 04/07/16.
 */
class GTDefaultConfig extends GTConfig {
    GTDefaultConfig() {
        super(false, GTApiConstants.GT_DEFAULT_APP_DOMAIN, true, "en");
    }
}
