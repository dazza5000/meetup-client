package com.bitgrind.meetup.api.options;

import com.bitgrind.meetup.api.model.Rsvp;

/**
 * Created by mrenouf on 1/5/15.
 */
public class GetRsvpsOptions extends CommonOptions<GetRsvpsOptions> {
    /**
     * Specifies the event rsvpId for RSVPs to request. (Required)
     *
     * @param eventId the event ID
     * @return this object for call chaining
     */
    public GetRsvpsOptions eventId(String eventId) {
        set("event_id", eventId);
        return this;
    }

    /**
     * Filters the result for only RSVPs with a specific response. The default
     * is to return all RSVPs.
     *
     * @param attending the RSVP response
     * @return this object for call chaining
     */
    public GetRsvpsOptions rsvp(Rsvp.Attending attending) {
        set("rsvp", attending.name().toLowerCase());
        return this;
    }
}
