package com.bitgrind.meetup.api.model;

public class AttendanceInfo {
    Member member;
    Rsvp rsvp;
    AttendanceStatus status;

    @Override
    public String toString() {
        return "AttendanceInfo{" +
                "member=" + member +
                ", rsvp=" + rsvp +
                ", status=" + status +
                '}';
    }
}
