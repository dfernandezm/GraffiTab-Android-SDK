package com.graffitab.graffitabsdk.constants;

/**
 * Created by david on 22/10/2016.
 */

public class GTApiConstants {

    public static final String GT_API_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

    // API constants
    public static String GT_DEFAULT_APP_DOMAIN = "graffitab.com";
    public static String GT_DEFAULT_PROTOCOL = "https";
    public static final String GT_API_URL =
            String.format("%s://%s/api", GT_DEFAULT_PROTOCOL, GT_DEFAULT_APP_DOMAIN);

    // - Users
    // -- Global
    public static final String USERS = GT_API_URL + "/users";
    public static final String EXTERNAL_PROVIDERS = GT_API_URL + "/externalproviders";

    // -- Login
    public static final String LOGIN_ENDPOINT = "login";
    public static final String LOGIN_WITH_EXTERNAL_PROVIDER = "externalproviders/login";
    public static final String LOGOUT = "logout";
    public static final String RESET_PASSWORD = "resetpassword";

}
