package com.bitgrind.meetup.api.options;

import com.bitgrind.meetup.api.model.Event;
import com.bitgrind.meetup.api.model.Rsvp;

public class GetEventsOptions extends CommonOptions<GetEventsOptions> {

    public GetEventsOptions eventId(long eventId) {
        set("event_id", eventId);
        return this;
    }

    public GetEventsOptions eventId(long eventId, long... others) {
        set("event_id", eventId, others);
        return this;
    }

    public GetEventsOptions groupDomain(String domain) {
        set("group_domain", domain);
        return this;
    }

    public GetEventsOptions groupId(long groupId) {
        set("group_id", groupId);
        return this;
    }

    public GetEventsOptions groupUrlName(String urlName) {
        set("group_urlname", urlName);
        return this;
    }

    public GetEventsOptions memberId(int memberId) {
        set("member_id", memberId);
        return this;
    }

    public GetEventsOptions limitedEvents(boolean include) {
        set("limited_events", include);
        return this;
    }

    enum RsvpStatus {
        YES, NO, WAITLIST, MAYBE, NONE;

        public String toString() {
            return name().toLowerCase();
        }
    }

    /**
     * Filters events by the currently authenticated member's RSVP status.
     */
    public GetEventsOptions rsvp(RsvpStatus status) {
        set("rsvp", status);
        return this;
    }

    /**
     * Filters events by the currently authenticated member's RSVP status.
     */
    public GetEventsOptions rsvp(RsvpStatus status, RsvpStatus... others) {
        set("rsvp", status, others);
        return this;
    }

    public GetEventsOptions venueId(int venueId) {
        set("venue_id", venueId);
        return this;
    }

    public GetEventsOptions venueId(int venueId, int... others) {
        set("venue_id", venueId, others);
        return this;
    }

    public GetEventsOptions status(Event.Status status) {
        set("status", status);
        return this;
    }

    public GetEventsOptions status(Event.Status status, Event.Status... others) {
        set("status", status, others);
        return this;
    }

    public enum TextFormat {
        HTML,
        PLAIN;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public GetEventsOptions textFormat(TextFormat format) {
        set("text_format", format.name().toLowerCase());
        return this;
    }
}
