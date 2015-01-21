package com.bitgrind.meetup.api.exceptions;

import java.util.Map;
import java.util.Objects;

import retrofit.RetrofitError;

/**
* Created by mrenouf on 1/16/15.
*/
public class BadRequestException extends GenericApiException {
    public BadRequestException(String message, Map<String, String> errors, RetrofitError cause) {
        super(Objects.toString(message, "Bad Request"), errors, cause);
    }
}
