package com.bitgrind.meetup.api.model;

/**
 * Created by mrenouf on 1/5/15.
 */
public class EventRsvpRules {
    enum Waitlisting {
        NONE,
        AUTO,
        OFF;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public long closeTime;

    public boolean closed;

    public int guestLimit;

    public long openTime;

    public Waitlisting waitlisting;
}
