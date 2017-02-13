package com.graffitabsdk.network.service.user;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.graffitabsdk.log.GTLog;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.user.persist.AccountsPersistor;
import com.graffitabsdk.sdk.cache.GTCacheService;
import com.graffitabsdk.network.call.GTNetworkTask;

import javax.inject.Inject;

import okhttp3.CookieJar;

/**
 * Created by david on 10/12/2016.
 */

public class GTLogoutTask extends GTNetworkTask {

    private UserService userService;
    private AccountsPersistor accountsPersistor;
    private GTCacheService cacheService;
    private CookieJar cookieJar;

    @Inject
    public GTLogoutTask(UserService userService, AccountsPersistor accountsPersistor, GTCacheService cacheService, CookieJar cookieJar) {
        this.userService = userService;
        this.cacheService = cacheService;
        this.accountsPersistor = accountsPersistor;
        this.cookieJar = cookieJar;
    }

    public GTRequestPerformed logout(GTResponseHandler<Void> responseHandler) {
        return performRawRequest(userService.logout(), responseHandler);
    }

    @Override
    protected void performExtraOperationOnSuccess(GTResponse<?> gtResponse) {
        clearUserAccount();
    }

    @Override
    protected void performExtraOperationOnFailure(GTResponse<?> gtResponse) {
        // If logout fails, we still want to clean up the user account.
        clearUserAccount();
    }

    private void clearUserAccount() {
        GTLog.i(getClass().getSimpleName(), "Clearing logged in user...", false);
        accountsPersistor.clearLoggedInUser();
        cacheService.invalidateCache();

        if (cookieJar instanceof ClearableCookieJar) {
            ((ClearableCookieJar) cookieJar).clearSession();
            ((ClearableCookieJar) cookieJar).clear();
            GTLog.i(getClass().getSimpleName(), "Cleared cookies", false);
        }
    }
}
