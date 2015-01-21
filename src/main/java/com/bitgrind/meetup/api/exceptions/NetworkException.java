package com.bitgrind.meetup.api.exceptions;

import java.util.Collections;

import retrofit.RetrofitError;

/**
* Created by mrenouf on 1/16/15.
*/
public class NetworkException extends MeetupException {
    public NetworkException(String message, RetrofitError cause) {
        super(message, Collections.<String, String>emptyMap(), cause);
    }
}
