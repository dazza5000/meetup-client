package com.bitgrind.meetup.api.exceptions;

import java.util.Map;
import java.util.Objects;

import retrofit.RetrofitError;

/**
* Created by mrenouf on 1/16/15.
*/
public class RateLimitException extends GenericApiException {
    public RateLimitException(String message, Map<String, String> errors, RetrofitError cause) {
        super(Objects.toString(message, "Rate Limited"), errors, cause);
    }
}
