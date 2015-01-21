package com.bitgrind.meetup.api;

import com.bitgrind.meetup.api.auth.AuthenticationInterceptor;
import com.bitgrind.meetup.api.auth.Credentials;
import com.bitgrind.meetup.api.exceptions.MeetupException;
import com.bitgrind.meetup.api.gson.EventSurveyAnswerDeserializer;
import com.bitgrind.meetup.api.gson.LowerCaseEnumTypeAdapterFactory;
import com.bitgrind.meetup.api.model.AttendanceList;
import com.bitgrind.meetup.api.model.AttendanceStatus;
import com.bitgrind.meetup.api.model.EventList;
import com.bitgrind.meetup.api.model.EventSurveyAnswer;
import com.bitgrind.meetup.api.model.Group;
import com.bitgrind.meetup.api.model.Member;
import com.bitgrind.meetup.api.model.MemberList;
import com.bitgrind.meetup.api.model.RsvpList;
import com.bitgrind.meetup.api.options.CommonOptions;
import com.bitgrind.meetup.api.options.GetAttendanceOptions;
import com.bitgrind.meetup.api.options.GetEventsOptions;
import com.bitgrind.meetup.api.options.GetMembersOptions;
import com.bitgrind.meetup.api.options.GetRsvpsOptions;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.client.UrlConnectionClient;
import retrofit.converter.GsonConverter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;

import static retrofit.RestAdapter.LogLevel;

/**
 * Defines the client interface to the Meetup API.
 * <p/>
 * Use a {@link Builder} to construct an instance.
 */
public interface MeetupClient {
    public static String DEFAULT_ENDPOINT = "https://api.meetup.com";

    // Sync
    // Performs blocking HTTP calls.

    @GET("/2/members")
    MemberList getMembers(
            @QueryMap GetMembersOptions opts)
            throws MeetupException;

    @GET("/{urlname}")
    Group getGroup(
            @Path("urlname") String groupUrlName,
            @QueryMap CommonOptions opts)
            throws MeetupException;

    @GET("/2/member/{member_id}")
    Member getMember(
            @Path("member_id") String memberId,
            @QueryMap CommonOptions commonOptions)
            throws MeetupException;

    @GET("/2/events")
    EventList getEvents(
            @QueryMap GetEventsOptions opts)
            throws MeetupException;

    @GET("/2/rsvps")
    RsvpList getRsvps(
            @QueryMap GetRsvpsOptions opts)
            throws MeetupException;

    @GET("/{urlname}/events/{event_id}/attendance")
    AttendanceList getAttendance(
            @Path("urlname") String groupUrlName,
            @Path("event_id") String eventId,
            @QueryMap GetAttendanceOptions opts)
            throws MeetupException;

    @POST("/{urlname}/events/{eventId}/attendance") @FormUrlEncoded
    Response recordAttendance(
            @Path("urlname") String groupUrlName,
            @Path("event_id") String eventId,
            @Field("member") long memberId,
            @Field("status") AttendanceStatus status)
            throws MeetupException;

    // Async interface
    // On Android, callbacks will be executed on the main thread. For all other platforms the
    // callback will be executed on the thread which executed the HTTP request.
    // No exceptions will be thrown, instead they are reported via the callback.

    @GET("/2/members")
    void getMembers(
            @QueryMap GetMembersOptions opts,
            Callback<MemberList> cb);

    @GET("/{urlname}")
    void getGroup(
            @Path("urlname") String groupUrlName,
            @QueryMap CommonOptions opts,
            Callback<Group> cb);

    @GET("/2/member/{memberId}")
    void getMember(
            @Path("memberId") String memberId,
            @QueryMap CommonOptions commonOptions,
            Callback<Member> cb);

    @GET("/2/events")
    void getEvents(
            @QueryMap GetEventsOptions opts,
            Callback<EventList> cb);

    @GET("/2/rsvps")
    void getRsvps(
            @QueryMap GetRsvpsOptions opts,
            Callback<RsvpList> cb);

    @GET("/{urlname}/events/{event_id}/attendance")
    void getAttendance(
            @Path("urlname") String groupUrlName,
            @Path("event_id") String eventId,
            @QueryMap GetAttendanceOptions opts,
            Callback<Response> result);

    @POST("/{urlname}/events/{eventId}/attendance") @FormUrlEncoded
    void recordAttendance(
            @Path("urlname") String groupUrlName,
            @Path("eventId") String eventId,
            @Field("member") long memberId,
            @Field("status") AttendanceStatus status,
            Callback<Response> result);

    /**
     * Visible for testing
     */
    static final Gson GSON = new GsonBuilder()
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapterFactory(new LowerCaseEnumTypeAdapterFactory())
            .registerTypeAdapter(EventSurveyAnswer.class,
                    new EventSurveyAnswerDeserializer())
            .create();


    public static class Builder {
        private AuthenticationInterceptor authInterceptor;
        private String endpointUrl = DEFAULT_ENDPOINT;
        private LogLevel logLevel = LogLevel.NONE;

        /**
         * Sets credentials to authenticate API calls before they are sent.
         *
         * @param credentials for authenticated API calls
         *                    (see {@link com.bitgrind.meetup.api.auth.ApiKey} and
         *                    {@link com.bitgrind.meetup.api.auth.OAuth2AccessToken})
         * @return this builder
         */
        public Builder withCredentials(Credentials credentials) {
            authInterceptor = new AuthenticationInterceptor(credentials);
            return this;
        }

        /**
         * Overrides the default Meetup API endpoint.
         *
         * @param endpointUrl the endpoint URL to use the API with
         * @return this builder
         */
        public Builder withEndpoint(String endpointUrl) {
            this.endpointUrl = endpointUrl;
            return this;
        }

        /**
         * Changes the log level from the default of {@link LogLevel#NONE}.
         *
         * @param level the level of debug logging output
         * @return this builder
         */
        public Builder withLogLevel(LogLevel level) {
            this.logLevel = level;
            return this;
        }

        /**
         * Builds an instance of the client.
         *
         * @return a new instance of MeetupClient
         */
        public MeetupClient build() {
            RestAdapter.Builder builder = new RestAdapter.Builder();
            builder.setEndpoint(endpointUrl)
                    .setClient(new UrlConnectionClient())
                    .setRequestInterceptor(authInterceptor)
                    .setErrorHandler(new MeetupErrorHandler())
                    .setLogLevel(logLevel)
                    .setConverter(new GsonConverter(GSON));
            if (authInterceptor != null) {
                builder.setRequestInterceptor(authInterceptor);
            }
            return builder.build().create(MeetupClient.class);
        }
    }
}

