package com.graffitabsdk.network.common;

/**
 * Created by david on 06/11/2016.
 */

public enum GTResultCode {

    OK(200),

    UNSUPPORTED_FILE_TYPE(400),
    INVALID_TOKEN(400),
    STREAM_COULD_NOT_BE_READ(400),
    INVALID_ARGUMENT(400),
    MISSING_ARGUMENT(400),
    INVALID_JSON(400),
    INVALID_FOLLOWEE(400),
    EMPTY_MANDATORY_FIELD(400),
    INVALID_USERNAME(400),
    INVALID_EMAIL(400),
    USERNAME_ALREADY_IN_USE(400),
    EMAIL_ALREADY_IN_USE(400),
    INVALID_ID(400),

    USER_NOT_LOGGED_IN(401),
    USER_NOT_OWNER(401),

    INCORRECT_PASSWORD(403),
    MAXIMUM_LOGIN_ATTEMPTS(403),
    FORBIDDEN_RESOURCE(403),

    EXTERNAL_PROVIDER_NOT_FOUND(404),
    EXTERNAL_PROVIDER_NOT_LINKED(404),
    DEVICE_NOT_FOUND(404),
    USER_NOT_FOUND(404),
    ASSET_NOT_FOUND(404),
    STREAMABLE_NOT_FOUND(404),
    COMMENT_NOT_FOUND(404),
    LOCATION_NOT_FOUND(404),
    TOKEN_NOT_FOUND(404),

    TOKEN_EXPIRED(406),
    USER_NOT_IN_EXPECTED_STATE(406),

    DEVICE_ALREADY_EXISTS(409),
    EXTERNAL_PROVIDER_ALREADY_LINKED(409),
    EXTERNAL_PROVIDER_ALREADY_LINKED_FOR_OTHER_USER(409),

    // Request was never made (network failure or host unreachable)
    OTHER(1),
    EMPTY(0),
    GENERAL_ERROR(500);

    private Integer statusCode;

    public Integer getStatusCode() {
        return this.statusCode;
    }

    GTResultCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
