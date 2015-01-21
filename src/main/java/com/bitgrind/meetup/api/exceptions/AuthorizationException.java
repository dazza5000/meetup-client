package com.bitgrind.meetup.api.exceptions;

import java.util.Map;
import java.util.Objects;

import retrofit.RetrofitError;

/**
* Created by mrenouf on 1/16/15.
*/
public class AuthorizationException extends GenericApiException {
    public AuthorizationException(String message, Map<String, String> errors, RetrofitError cause) {
        super(Objects.toString(message, "Not Authorized"), errors, cause);
    }
}
