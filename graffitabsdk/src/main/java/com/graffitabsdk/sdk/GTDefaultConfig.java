package com.graffitabsdk.sdk;

import com.graffitabsdk.constants.GTApiConstants;

/**
 * Created by georgichristov on 04/07/16.
 */
class GTDefaultConfig extends GTConfig {
    GTDefaultConfig() {
        super(false, GTApiConstants.GT_DEFAULT_DOMAIN, GTApiConstants.GT_DEFAULT_SECURE, GTApiConstants.GT_DEFAULT_LANGUAGE);
    }
}
