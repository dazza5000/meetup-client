package com.bitgrind.meetup.api.auth;

import com.bitgrind.meetup.api.gson.LowerCaseEnumTypeAdapterFactory;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Contains operations needed to obtain an OAuth2 access token for a Meetup user.
 */
public class MeetupOAuth2Client {

    public static final URL MEETUP_OAUTH2_ACCESS;
    public static final URL MEETUP_OAUTH2_AUTHORIZE;
    static {
        try {
            MEETUP_OAUTH2_ACCESS = new URL("https://secure.meetup.com/oauth2/access");
            MEETUP_OAUTH2_AUTHORIZE = new URL("https://secure.meetup.com/oauth2/authorize");
        } catch (MalformedURLException e) {
            throw new RuntimeException("BAD URL?", e);
        }
    }

    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE = "grant_type";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String CODE = "code";
    private static final String REFRESH_TOKEN = "refresh_token";

    private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    private static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";

    private final Gson gson;

    private final String consumerKey;
    private final String consumerSecret;
    private final String redirectUrl;

    /**
     * Constructs a new client.
     *
     * @param consumerKey    the public portion of the OAuth2 consumer credentials
     * @param consumerSecret the private portion of the OAuth2 consumer credentials
     * @param redirectUrl    the redirect URL where meetup will send clients
     *                       after approving the access grant
     */
    public MeetupOAuth2Client(String consumerKey, String consumerSecret, String redirectUrl) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.redirectUrl = redirectUrl;
        this.gson = new GsonBuilder()
                .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapterFactory(new LowerCaseEnumTypeAdapterFactory())
                .create();
    }

    /**
     * Creates the authorization request URL.
     *
     * @param state an optional string which will be provided when the client returns
     * @return the redirect URL
     */
    public String createAuthorizationUrl(String state) {
        try {
            return String.format(MEETUP_OAUTH2_AUTHORIZE.toExternalForm() +
                            "?client_id=%s&response_type=code&redirect_uri=%s&state=%s",
                    consumerKey,
                    URLEncoder.encode(redirectUrl, "UTF-8"),
                    URLEncoder.encode(state, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the redirected request from Meetup containing the result of the authorization request.
     *
     * @param authorizationCode the value provided in the 'code' parameter
     * @return an access token response
     */
    public AccessTokenResponse requestAccessToken(String authorizationCode) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) MEETUP_OAUTH2_ACCESS.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty(CLIENT_ID, consumerKey);
        conn.setRequestProperty(CLIENT_SECRET, consumerSecret);
        conn.setRequestProperty(GRANT_TYPE, GRANT_TYPE_AUTHORIZATION_CODE);
        conn.setRequestProperty(REDIRECT_URI, redirectUrl);
        conn.setRequestProperty(CODE, authorizationCode);
        conn.setDoInput(true);
        Reader reader = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
        return gson.fromJson(reader, AccessTokenResponse.class);
    }

    /**
     * Requests a refresh of the given token.
     *
     * @param refreshToken the access_token to refresh
     * @return the result of the refresh request
     */
    public AccessTokenResponse refreshAccessToken(String refreshToken) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) MEETUP_OAUTH2_ACCESS.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty(CLIENT_ID, consumerKey);
        conn.setRequestProperty(CLIENT_SECRET, consumerSecret);
        conn.setRequestProperty(GRANT_TYPE, GRANT_TYPE_REFRESH_TOKEN);
        conn.setRequestProperty(REDIRECT_URI, redirectUrl);
        conn.setRequestProperty(REFRESH_TOKEN, refreshToken);
        conn.setDoInput(true);
        Reader reader = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
        return gson.fromJson(reader, AccessTokenResponse.class);
    }
}
