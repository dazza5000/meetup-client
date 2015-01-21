package com.bitgrind.meetup.api.model;

import java.util.List;

public class Rsvp {
    public long rsvpId;
    public Response response;
    public Member member;
    public Event event;
    public Group group;
    public Venue venue;
    public Photo memberPhoto;
    public long created;
    public long mtime;

    public Tallies tallies;

    public List<EventSurveyAnswer> answers;
    public boolean host;
    public int guests;
    public String comments;

    public PayStatus payStatus;

    @Override
    public String toString() {
        return "Rsvp{" +
                "response=" + response + "," +
                "id=" + rsvpId + "," +
                ((member != null) ? "member=" + member.memberId : "") +
                ((event != null) ? "event=" + event.id+ "," : "") +
                '}';
    }

    public static class Event {
        public String id;
        public long time;
        public String eventUrl;
        String name;
    }

    public static class Group {
        enum JoinMode {
            APPROVAL,
            OPEN,
            CLOSED;
            public String toString() {
                return name().toLowerCase();
            }
        }

        public int id;
        public long created;
        public double groupLat;
        public double groupLon;
        public JoinMode joinMode;
        public String urlname;
    }

    public static class Member {
        public long memberId;
        public String name;
    }
    public enum Attending {
        YES,
        NO;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum Response {
        YES,
        NO,
        MAYBE,
        WAITLIST,
        YES_PENDING_PAYMENT;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum PayStatus {
        NONE,
        PAID,
        PARTIALLY_PAID,
        PAYMENT_PENDING,
        ECHECK_PENDING,
        REFUND_PENDING,
        PARTIALLY_REFUNDED,
        REFUNDED;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public static class Photo {
        public long photoId;
        public String thumbUrl;
        public String photoUrl;
        public String highresUrl;
    }

    public static class Tallies {
        public int yes;
        public int maybe;
        public int no;
        public int waitlist;
    }
}
