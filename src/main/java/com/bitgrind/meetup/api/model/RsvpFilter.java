package com.bitgrind.meetup.api.model;

enum RsvpFilter {
    MAYBE,
    WAITLIST,
    YES,
    ABSENT,
    ALL,
    ATTENDED,
    NOSHOW,
    EXCUSED,
    NO;

    public String toString() {
        return name().toLowerCase();
    }
}
