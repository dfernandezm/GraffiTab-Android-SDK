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
    public static final String USER_EXTERNAL_PROVIDERS_ENDPOINT = USERS_ENDPOINT + "/externalproviders";

    // -- Login
    public static final String USER_LOGIN_ENDPOINT = "login";
    public static final String USER_LOGIN_WITH_EXTERNAL_PROVIDER_ENDPOINT = "externalproviders/login";
    public static final String USER_LOGOUT_ENDPOINT = "logout";
    public static final String USER_RESET_PASSWORD = USERS_ENDPOINT + "/resetpassword";

    // -- Profile
    public static final String USER_PROFILE_ENDPOINT = USERS_ENDPOINT + "/{userId}";
    public static final String USER_FULL_PROFILE_ENDPOINT = USER_PROFILE_ENDPOINT + "/profile";
    public static final String USER_PROFILE_BY_USERNAME_ENDPOINT = USERS_ENDPOINT + "/username/{username}";
    public static final String USER_FULL_PROFILE_BY_USERNAME_ENDPOINT = USER_PROFILE_BY_USERNAME_ENDPOINT + "/profile";

    // -- Followers
    public static final String USER_FOLLOWERS_ENDPOINT = USERS_ENDPOINT + "/{userId}/followers";
    public static final String USER_FOLLOWING_ENDPOINT = USERS_ENDPOINT + "/{userId}/following";

    // -- Streamables
    public static final String USER_STREAMABLES_ENDPOINT = USERS_ENDPOINT + "/{userId}/streamables";

    // -- Mentions
    public static final String USER_MENTIONS_ENDPOINT = USERS_ENDPOINT + "/{userId}/mentions";

    // -- Most Active
    public static final String USERS_MOST_ACTIVE_ENDPOINT = USERS_ENDPOINT + "/mostactive";

    // -- Likes
    public static final String USER_LIKES_ENDPOINT = USERS_ENDPOINT + "/{userId}/liked";

    // -- Search
    public static final String USERS_SEARCH_ENDPOINT = USERS_ENDPOINT + "/search";


    // - Me


    // -- Global
    public static final String ME_ENDPOINT = USERS_ENDPOINT + "/me";

    // -- Profile
    public static final String MY_FULL_PROFILE_ENDPOINT = ME_ENDPOINT + "/profile";

    // -- Edit
    public static final String ME_CHANGE_PASSWORD_ENDPOINT = ME_ENDPOINT + "/changepassword";

    // -- Avatar
    public static final String MY_AVATAR = ME_ENDPOINT + "/avatar";

    // -- Cover
    public static final String MY_COVER = ME_ENDPOINT + "/cover";

    // -- Notifications
    public static final String MY_NOTIFICATIONS_ENDPOINT = ME_ENDPOINT + "/notifications";
    public static final String MY_UNREAD_NOTIFICATIONS_COUNT_ENDPOINT = MY_NOTIFICATIONS_ENDPOINT + "/unreadcount";

    // -- Locations
    public static final String MY_LOCATIONS_ENDPOINT = ME_ENDPOINT + "/locations";
    public static final String MY_LOCATION_ENDPOINT = MY_LOCATIONS_ENDPOINT + "/{locationId}";

    // -- External Providers
    public static final String MY_EXTERNAL_PROVIDERS_ENDPOINT = ME_ENDPOINT + "/externalproviders";

    // -- Devices
    public static final String MY_DEVICES_ENDPOINT = ME_ENDPOINT + "/devices";

    // -- Followers
    public static final String MY_FOLLOWERS_ENDPOINT = ME_ENDPOINT + "/followers";
    public static final String MY_FOLLOWERS_ACTIVITY_ENDPOINT = MY_FOLLOWERS_ENDPOINT + "/activity";
    public static final String MY_FOLLOWING_ENDPOINT = ME_ENDPOINT + "/following";

    // -- Streamables
    public static final String MY_STREAMABLES_ENDPOINT = ME_ENDPOINT + "/streamables";
    public static final String MY_STREAMABLE_ENDPOINT = MY_STREAMABLES_ENDPOINT + "/{streamableId}";
    public static final String MY_GRAFFITI_ENDPOINT = MY_STREAMABLES_ENDPOINT + "/graffiti";
    public static final String MY_GRAFFIT_ENDPOINT = MY_GRAFFITI_ENDPOINT + "/{streamableId}";

    // -- Private Streamables
    public static final String MY_PRIVATE_STREAMABLE_ENDPOINT = MY_STREAMABLE_ENDPOINT + "/private";
    public static final String MY_PRIVATE_ENDPOINT = MY_STREAMABLES_ENDPOINT + "/private";

    // -- Feed
    public static final String MY_FEED_ENDPOINT = ME_ENDPOINT + "/feed";

    // -- Likes
    public static final String MY_LIKES_ENDPOINT = ME_ENDPOINT + "/liked";

    // -- Social
    public static final String MY_SOCIAL_ENDPOINT = ME_ENDPOINT + "/social";
    public static final String MY_SOCIAL_TYPE_ENDPOINT = MY_SOCIAL_ENDPOINT + "/{socialType}";
    public static final String ME_FILTER_SOCIAL_FRIENDS_ENDPOINT = MY_SOCIAL_TYPE_ENDPOINT + "/friends";
    public static final String MY_SOCIAL_FRIENDS_ENDPOINT = ME_FILTER_SOCIAL_FRIENDS_ENDPOINT;
    public static final String ME_IMPORT_AVATAR_ENDPOINT = MY_SOCIAL_TYPE_ENDPOINT + "/avatar";


    // - Streamables


    // -- Global
    public static final String STREAMABLES_ENDPOINT = "streamables";

    // -- Streamable
    public static final String STREAMABLE_ENDPOINT = STREAMABLES_ENDPOINT + "/{streamableId}";

    // -- Likes
    public static final String STREAMABLE_LIKES_ENDPOINT = STREAMABLE_ENDPOINT + "/likes";

    // -- Comments
    public static final String STREAMABLE_COMMENTS_ENDPOINT = STREAMABLE_ENDPOINT + "/comments";
    public static final String STREAMABLE_COMMENT_ENDPOINT = STREAMABLE_COMMENTS_ENDPOINT + "/{commentId}";

    // -- Newest
    public static final String STREAMABLES_NEWEST_ENDPOINT = STREAMABLES_ENDPOINT + "/newest";

    // -- Popular
    public static final String STREAMABLES_POPULAR_ENDPOINT = STREAMABLES_ENDPOINT + "/popular";

    // -- Flag
    public static final String STREAMABLE_FLAG_ENDPOINT = STREAMABLE_ENDPOINT + "/flag";

    // -- Search
    public static final String STREAMABLES_SEARCH_ENDPOINT = STREAMABLES_ENDPOINT + "/search";
    public static final String STREAMABLES_SEARCH_LOCATION_ENDPOINT = STREAMABLES_SEARCH_ENDPOINT + "/location";
    public static final String STREAMABLES_SEARCH_HASHTAG_ENDPOINT = STREAMABLES_SEARCH_ENDPOINT + "/hashtag";
    public static final String STREAMABLES_SEARCH_HASHTAGS_ENDPOINT = STREAMABLES_SEARCH_ENDPOINT + "/hashtags";


    // - Feedback


    // -- Global
    public static final String FEEDBACK_ENDPOINT = "feedback";

    // - Asset
    // -- Multipart uploads
    public static final String UPLOAD_AVATAR_ENDPOINT = "users/me/avatar";
    public static final String UPLOAD_COVER_ENDPOINT = "users/cover/avatar";

    // -- Global
    public static final String ASSETS_ENDPOINT = "assets";

    // -- Asset
    public static final String ASSET_ENDPOINT = ASSETS_ENDPOINT + "/{guid}";

    // -- Progress
    public static final String ASSET_PROGRESS_ENDPOINT = ASSET_ENDPOINT + "/progress";

}
