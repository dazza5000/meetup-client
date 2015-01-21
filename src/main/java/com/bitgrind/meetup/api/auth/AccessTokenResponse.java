package com.bitgrind.meetup.api.auth;

import java.io.Serializable;

public class AccessTokenResponse implements Serializable {
    public enum Error {
        INVALID_REQUEST("The request was malformed or missing parameters"),
        INVALID_CLIENT("Client authentication failed"),
        UNAUTHORIZED_CLIENT("The provided client was not authorized to use this grant type"),
        INVALID_GRANT("The provided code was invalid"),
        UNSUPPORTED_GRANT_TYPE("Meetup does not support the provided grant type");

        private final String desc;

        private Error(String desc) {
            this.desc = desc;
        }

        public String getDescription() {
            return desc;
        }
    }

    private String accessToken;
    private String tokenType;
    private int expiresIn;
    private String refreshToken;
    private Error error;

    public boolean isError() {
        return error != null;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Error getError() {
        return error;
    }
}
