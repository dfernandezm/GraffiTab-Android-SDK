package com.graffitab.graffitabsdk;

import android.util.Log;
import com.graffitabsdk.sdk.GTConfig;
import com.graffitabsdk.sdk.GTSDK;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by david on 05/11/2016.
 */

public class ConfigurationTest {

    @Mock
    private Log Log;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setCustomConfigTest() {
        GTConfig customConfig = GTConfig.defaultConfig();
        customConfig.logEnabled = true;
        customConfig.domain = "dev.graffitab.com";
        GTSDK.init(null, customConfig);

        Assert.assertEquals(GTSDK.getConfig().getDomain(), "dev.graffitab.com");
        Assert.assertEquals(GTSDK.getConfig().getLanguage(), "en");
        Assert.assertTrue(GTSDK.getConfig().isHttpsEnabled());
        Assert.assertTrue(GTSDK.getConfig().isLogEnabled());
        Assert.assertEquals(GTSDK.getConfig().buildBaseApiUrl(), "https://dev.graffitab.com/api/");
    }
}
