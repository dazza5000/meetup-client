package com.bitgrind.meetup.api.auth;

import retrofit.RequestInterceptor;

import static retrofit.RequestInterceptor.RequestFacade;

/**
 * Authenticates requests using a static API Key. This should only be used in
 * situations where the key can be protected from disclosure.
 * <p/>
 * This should only be used with requests made over SSL. If intercepted, the key
 * can be used by anyone until revoked.
 */
public class ApiKey implements Credentials {
    public static final String MEETUP_API_KEY = "meetup.api_key";

    private final String key;

    public ApiKey(String key) {
        this.key = key;
    }

    public void applyTo(RequestFacade request) {
        request.addQueryParam("key", key);
    }
}
