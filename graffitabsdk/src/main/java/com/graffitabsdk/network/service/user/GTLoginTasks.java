package com.graffitabsdk.network.service.user;

import com.graffitabsdk.model.GTExternalProvider;
import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.response.GTResponse;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.service.user.response.GTUserResponse;
import com.graffitabsdk.network.call.GTNetworkTask;
import com.graffitabsdk.network.service.user.persist.AccountsPersistor;

import javax.inject.Inject;

/**
 * Created by david on 10/12/2016.
 */

public class GTLoginTasks extends GTNetworkTask {

    private UserService userService;
    private AccountsPersistor accountsPersistor;
    private String lastUsedPassword;

    @Inject
    public GTLoginTasks(UserService userService, AccountsPersistor accountsPersistor) {
        this.userService = userService;
        this.accountsPersistor = accountsPersistor;
    }

    public GTRequestPerformed login(String username, String password, GTResponseHandler<GTUserResponse> responseHandler) {
        this.lastUsedPassword = password;

        LoginData loginData = new LoginData(username, password);
        return performJsonRequest(userService.login(loginData), GTUserResponse.class, responseHandler);
    }

    public GTRequestPerformed login(String externalId, String externalToken, GTExternalProvider.GTExternalProviderType externalType, GTResponseHandler<GTUserResponse> responseHandler) {
        LoginExternalProviderMetadata loginExternalProviderMetadata = new LoginExternalProviderMetadata(externalId, externalToken, externalType);
        LoginExternalProviderData loginExternalProviderData = new LoginExternalProviderData(loginExternalProviderMetadata);
        return performJsonRequest(userService.loginExternal(loginExternalProviderData), GTUserResponse.class, responseHandler);
    }

    @Override
    protected void performExtraOperationOnSuccess(GTResponse<?> gtResponse) {
        GTUser user = ((GTUserResponse) gtResponse.getObject()).user;
        accountsPersistor.saveLoggedInUser(user);
        accountsPersistor.saveLastLoggedInUser(user, lastUsedPassword);
    }
}
