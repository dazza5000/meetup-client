package com.bitgrind.meetup.api.model;

public enum AttendanceStatus {
    NOSHOW,
    ABSENT,
    ATTENDED;

    public String toString() {
        return name().toLowerCase();
    }
}
