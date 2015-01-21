package com.bitgrind.meetup.api.auth;

import retrofit.RequestInterceptor;

/**
 * Authenticates requests using an OAuth2 access token.
 * <p/>
 * The token is supplied in a header field. This should only be used with SSL requests.
 * to avoid disclosing of the access token to third parties. Access tokens are valid for
 * 1 hour by default.
 */
public class OAuth2AccessToken implements Credentials {

    private final String accessToken;

    public OAuth2AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void applyTo(RequestInterceptor.RequestFacade request) {
        request.addHeader("Authorization", "Bearer " + accessToken);

    }
}
