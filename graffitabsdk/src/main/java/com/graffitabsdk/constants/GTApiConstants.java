package com.graffitabsdk.constants;

/**
 * Created by david on 22/10/2016.
 */

public class GTApiConstants {

    public static final String GT_API_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

    // Default API constants
    public static final String GT_DEFAULT_DOMAIN = "www.graffitab.com";
    public static final String GT_DEFAULT_LANGUAGE = "en";
    public static final boolean GT_DEFAULT_SECURE = true;

    // - Users
    // -- Global
    public static final String USERS_ENDPOINT = "users";
    public static final String ME_ENDPOINT = USERS_ENDPOINT + "/me";
    public static final String MY_FULL_PROFILE_ENDPOINT = ME_ENDPOINT + "/profile";
    public static final String EXTERNAL_PROVIDERS = "externalproviders";

    // -- Login
    public static final String LOGIN_ENDPOINT = "login";
    public static final String LOGIN_WITH_EXTERNAL_PROVIDER = "externalproviders/login";
    public static final String LOGOUT = "logout";
    public static final String RESET_PASSWORD = "resetpassword";

}
