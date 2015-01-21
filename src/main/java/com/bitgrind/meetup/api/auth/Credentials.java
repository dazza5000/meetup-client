package com.bitgrind.meetup.api.auth;

import static retrofit.RequestInterceptor.RequestFacade;

public interface Credentials {
    void applyTo(RequestFacade request);
}
