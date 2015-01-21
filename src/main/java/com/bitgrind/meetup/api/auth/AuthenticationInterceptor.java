package com.bitgrind.meetup.api.auth;

import retrofit.RequestInterceptor;

public class AuthenticationInterceptor implements RequestInterceptor {
    private Credentials credentials;

    public AuthenticationInterceptor(Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public void intercept(RequestFacade request) {
        credentials.applyTo(request);
    }
}
