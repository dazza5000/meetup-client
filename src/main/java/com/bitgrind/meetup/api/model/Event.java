package com.bitgrind.meetup.api.model;


import java.util.List;

/**
 * Created by mrenouf on 12/31/14.
 */
public class Event {

    public enum Status {
        CANCELLED,
        UPCOMING,
        PAST,
        PROPOSED,
        SUGGESTED,
        DRAFT;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum PublishStatus {
        PUBLISHED,
        DRAFT;

        public String toString() {
            return name().toLowerCase();
        }
    }

    enum Visibility {
        MEMBERS,
        PUBLIC,
        PUBLIC_LIMITED;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public boolean announced;

    public long announcedAt;

    public long commentCount;

    public long created;

    public String description;

    public int duration;

    public boolean emailReminders;

    public List<EventHost> eventHosts;

    public String eventUrl;

    public boolean featured;

    public EventFee fee;

    public Group group;

    public int headcount;

    public String howToFindUs;

    public String id;

    public boolean isSimplehtml;

    public int maybeRsvpCount;

    public String name;

    public String photoAlbumId;

    public int photoCount;

    public String photoUrl;

    public PublishStatus publishStatus;

    public EventRating rating;

    public boolean rsvpAlerts;

    public int rsvpLimit;

    public EventRsvpRules rsvpRules;

    public String shortLink;

    public String simpleHtmlDescription;

    public Status status;

    public List<EventSurveyQuestion> surveyQuestions;

    public long time;

    public String timezone;

    public int trendingRank;

    public long updated;

    public int utcOffset;

    public Venue venue;

    public Venue.Visibility venueVisibility;

    public Event.Visibility visibility;

    public String why;

    /**
     * Undocumented at http://www.meetup.com/meetup_api/docs/2/events/
     */
    public int waitlistCount;

    public int yesRsvpCount;
}
