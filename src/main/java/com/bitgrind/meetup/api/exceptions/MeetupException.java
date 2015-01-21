package com.bitgrind.meetup.api.exceptions;

import java.util.Collections;
import java.util.Map;

import retrofit.RetrofitError;

/**
* Created by mrenouf on 1/16/15.
*/
public class MeetupException extends RuntimeException {
    private final Map<String, String> errors;

    public MeetupException(String message, Map<String, String> errors, RetrofitError cause) {
        super(message, cause);
        this.errors = Collections.unmodifiableMap(errors);
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
