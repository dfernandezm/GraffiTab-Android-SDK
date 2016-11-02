package com.graffitab.graffitabsdk;

import android.util.Log;

import com.graffitab.graffitabsdk.config.GTConfig;
import com.graffitab.graffitabsdk.config.GTSDKConfig;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

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
    public void defaultConfigTest() {
        GTSDKConfig.set(GTConfig.defaultConfig());
        Assert.assertEquals(GTSDKConfig.get().getDomain(), "dev.graffitab.com");
        Assert.assertEquals(GTSDKConfig.get().getLanguage(), "en");
        Assert.assertFalse(GTSDKConfig.get().isHttpsEnabled());
        Assert.assertFalse(GTSDKConfig.get().isLogEnabled());
        Assert.assertEquals(GTSDKConfig.get().buildBaseApiUrl(), "http://dev.graffitab.com/api");
    }

    @Test
    public void setCustomConfigTest() {
        GTConfig customConfig = GTConfig.defaultConfig();
        customConfig.logEnabled = true;
        customConfig.httpsEnabled = true;
        customConfig.domain = "www.graffitab.com";
        GTSDKConfig.set(customConfig);

        Assert.assertEquals(GTSDKConfig.get().getDomain(), "www.graffitab.com");
        Assert.assertEquals(GTSDKConfig.get().getLanguage(), "en");
        Assert.assertTrue(GTSDKConfig.get().isHttpsEnabled());
        Assert.assertTrue(GTSDKConfig.get().isLogEnabled());
        Assert.assertEquals(GTSDKConfig.get().buildBaseApiUrl(), "https://www.graffitab.com/api");
    }




}
