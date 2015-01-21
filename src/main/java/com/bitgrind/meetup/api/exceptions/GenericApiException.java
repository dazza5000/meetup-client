package com.bitgrind.meetup.api.exceptions;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import retrofit.RetrofitError;

/**
* Created by mrenouf on 1/16/15.
*/
public class GenericApiException extends MeetupException {
    public GenericApiException(String message, Map<String, String> errors, RetrofitError cause) {
        super(Objects.toString(message, "Unknown Error"), Collections.<String, String>emptyMap(), cause);
    }
}
