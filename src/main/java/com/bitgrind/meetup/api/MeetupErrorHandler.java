package com.bitgrind.meetup.api;

import com.bitgrind.meetup.api.exceptions.AuthorizationException;
import com.bitgrind.meetup.api.exceptions.BadRequestException;
import com.bitgrind.meetup.api.exceptions.GenericApiException;
import com.bitgrind.meetup.api.exceptions.NetworkException;
import com.bitgrind.meetup.api.exceptions.RateLimitException;
import com.bitgrind.meetup.api.exceptions.ServerException;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

/**
 * Handles errors encountered with the Meetup API.
 * <p/>
 * All failures will result in unchecked exceptions. All known errors are {@link com.bitgrind.meetup.api.exceptions.MeetupException}
 * or a subclass. It's recommended that at a minimum AuthorizationException and RateLimitException
 * be caught and handled, since these are potentially recoverable conditions.
 */
public class MeetupErrorHandler implements ErrorHandler {
    @Override
    public Throwable handleError(RetrofitError cause) {
        Response response = cause.getResponse();
        switch (cause.getKind()) {
            case CONVERSION:
                return new JsonParseException("Conversion from JSON failed", cause);
            case NETWORK:
                throw new NetworkException("Connection failed", cause);
            case HTTP:
                // HTTP errors throw a subclass of GenericApiException
                String message = null;
                Map<String, String> errorMap = Collections.emptyMap();
                ErrorResponse errors = (ErrorResponse) cause.getBodyAs(ErrorResponse.class);
                if (errors != null) {
                    errorMap = errors.asMap();
                    message = summarizeErrors(errorMap);
                }
                switch (response.getStatus()) {
                    case 400: // Bad request - when there was a problem with the request
                        return new BadRequestException(message, errorMap, cause);
                    case 401: // Unauthorized - when you don't provide a valid key
                        return new AuthorizationException(message, errorMap, cause);
                    case 429: // Too Many Requests - when you've gone over your request rate limit
                        return new RateLimitException(message, errorMap, cause);
                    case 500: // Internal Server Error - if we messed up -- please let us know!
                        return new ServerException(message, errorMap, cause);
                    default: // All other HTTP status codes
                        return new GenericApiException(message, errorMap, cause);
                }
            case UNEXPECTED:
                throw cause;
            default:
                throw new RuntimeException("Unhandled Error Kind: " + cause.getKind(), cause);
        }
    }

    public static ErrorResponse readErrorResponse(Response response) {
        TypedInput body = response.getBody();
        if (body.mimeType().contains("/json")) {
            try {
                Reader reader = new InputStreamReader(body.in(), StandardCharsets.UTF_8);
                return MeetupClient.GSON.fromJson(reader, ErrorResponse.class);
            } catch (IOException ex) { /* Swallowed */ }
        }
        return null;
    }

    private static String summarizeErrors(Map<String, String> errors) {
        StringBuilder sb = new StringBuilder();
        if (errors.size() > 1) {
            sb.append("[");
        }
        Iterator<Map.Entry<String, String>> i = errors.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<String, String> entry = i.next();
            sb.append(entry.getKey()).append(": ");
            sb.append("'").append(entry.getValue()).append("'");
            if (i.hasNext()) {
                sb.append(", ");
            }
        }
        if (errors.size() > 1) {
            sb.append("]");
        }
        return sb.toString();
    }

    public static class ErrorResponse {
        public static class ApiError {
            String code;
            String message;

            public String toString() {
                return "ApiError{code='" + code + "', message='" + message + "'}";
            }
        }

        // Errors from v1 & v2 API
        /*
        {
            "details":"Perhaps you're missing a required parameter.
                        You can find full documentation of the api here:
                        http:\/\/www.meetup.com\/meetup_api\/docs\/",
            "problem":"The API request is malformed",
            "code":"bad_request"
        }
        */
        String code;
        String problem;
        String details;

        // Errors from v3 API
        /*
        {
            "errors":[
                {
                    "code":"group_error",
                    "message":"Invalid group urlname"
                }
            ]
        }
        */
        ApiError[] errors;


        public Map<String, String> asMap() {
            Map<String, String> map = new HashMap<>(errors != null ? errors.length : 1);

            StringBuilder sb = new StringBuilder("Errors:");
            if (errors != null) {
                for (ApiError error : errors) {
                    map.put(error.code, error.message);
                }
            } else {
                map.put(code, problem +
                        ((details != null && !details.isEmpty()) ? " (" + details + ")" : ""));
            }
            return map;
        }
    }
}
