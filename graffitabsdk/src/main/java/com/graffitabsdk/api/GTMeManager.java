package com.graffitabsdk.api;

import com.graffitabsdk.network.common.GTRequestPerformed;
import com.graffitabsdk.network.common.params.GTQueryParameters;
import com.graffitabsdk.network.common.response.GTResponseHandler;
import com.graffitabsdk.network.common.result.GTActionCompleteResult;
import com.graffitabsdk.network.service.location.response.GTListLocationsResponse;
import com.graffitabsdk.network.service.location.response.GTLocationResponse;
import com.graffitabsdk.network.service.notification.response.GTListNotificationsResponse;
import com.graffitabsdk.network.service.streamable.response.GTListStreamablesResponse;
import com.graffitabsdk.network.service.user.response.GTUnseenNotificationsResponse;
import com.graffitabsdk.network.service.user.response.GTUserResponse;
import com.graffitabsdk.network.service.device.GTDeviceTasks;
import com.graffitabsdk.network.service.location.GTLocationTasks;
import com.graffitabsdk.network.service.notification.GTNotificationTasks;
import com.graffitabsdk.network.service.streamable.GTStreamableTasks;
import com.graffitabsdk.network.service.user.GTUserProfileTasks;
import com.graffitabsdk.network.service.user.GTUserTasks;

import javax.inject.Inject;

/**
 * Created by georgichristov on 16/01/2017
 * --
 * Copyright © GraffiTab Inc. 2016
 */
public class GTMeManager {

    private GTUserTasks gtUserTasks;
    private GTStreamableTasks gtStreamableTasks;
    private GTNotificationTasks gtNotificationTasks;
    private GTDeviceTasks gtDeviceTasks;
    private GTLocationTasks gtLocationTasks;
    private GTUserProfileTasks gtUserProfileTasks;

    @Inject
    public GTMeManager(GTUserTasks userTasks, GTStreamableTasks gtStreamableTasks, GTNotificationTasks gtNotificationTasks, GTLocationTasks gtLocationTasks, GTUserProfileTasks gtUserProfileTasks, GTDeviceTasks gtDeviceTasks) {
        this.gtUserTasks = userTasks;
        this.gtStreamableTasks = gtStreamableTasks;
        this.gtNotificationTasks = gtNotificationTasks;
        this.gtLocationTasks = gtLocationTasks;
        this.gtUserProfileTasks = gtUserProfileTasks;
        this.gtDeviceTasks = gtDeviceTasks;
    }

    public GTRequestPerformed getMe(boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtUserProfileTasks.getMe(useCache, responseHandler);
    }

    public GTRequestPerformed getMyFullProfile(boolean useCache, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtUserProfileTasks.getMyFullProfile(useCache, responseHandler);
    }

    public GTRequestPerformed edit(String firstName, String lastName, String email, String about, String website, GTResponseHandler<GTUserResponse> responseHandler) {
        return gtUserProfileTasks.edit(firstName, lastName, email, about, website, responseHandler);
    }

    public GTRequestPerformed editPassword(String currentPassword, String newPassword, GTResponseHandler<GTActionCompleteResult> responseHandler) {
        return gtUserTasks.editPassword(currentPassword, newPassword, responseHandler);
    }

    public GTRequestPerformed getFeed(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return gtStreamableTasks.getFeed(useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getNotifications(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListNotificationsResponse> responseHandler) {
        return gtNotificationTasks.getNotifications(useCache, parameters, responseHandler);
    }

    public GTRequestPerformed getUnseenNotifications(GTResponseHandler<GTUnseenNotificationsResponse> responseHandler) {
        return gtNotificationTasks.getUnseenNotifications(responseHandler);
    }

    public GTRequestPerformed getLocations(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListLocationsResponse> responseHandler) {
        return gtLocationTasks.getLocations(useCache, parameters, responseHandler);
    }

    public GTRequestPerformed createLocation(String address, double latitude, double longitude, GTResponseHandler<GTLocationResponse> responseHandler) {
        return gtLocationTasks.createLocation(address, latitude, longitude, responseHandler);
    }

    public GTRequestPerformed editLocation(int locationId, String address, double latitude, double longitude, GTResponseHandler<GTLocationResponse> responseHandler) {
        return gtLocationTasks.editLocation(locationId, address, latitude, longitude, responseHandler);
    }

    public GTRequestPerformed deleteLocation(int locationId, GTResponseHandler<GTActionCompleteResult> responseHandler) {
        return gtLocationTasks.deleteLocation(locationId, responseHandler);
    }

    public GTRequestPerformed getPrivatePosts(boolean useCache, GTQueryParameters parameters, GTResponseHandler<GTListStreamablesResponse> responseHandler) {
        return gtUserTasks.getPrivatePosts(useCache, parameters, responseHandler);
    }

    public GTRequestPerformed linkDevice(String token, GTResponseHandler<GTActionCompleteResult> responseHandler) {
        return gtDeviceTasks.linkDevice(token, responseHandler);
    }

    public GTRequestPerformed unlinkDevice(String token, GTResponseHandler<GTActionCompleteResult> responseHandler) {
        return gtDeviceTasks.unlinkDevice(token, responseHandler);
    }
}
