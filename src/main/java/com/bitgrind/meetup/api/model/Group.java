package com.bitgrind.meetup.api.model;

import java.util.List;

public class Group {
    enum JoinMode {
        APPROVAL,
        OPEN,
        CLOSED;
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

    int id;
    String name;
    String urlname;
    String description;
    long created;
    String city;
    String country;
    String state;

    JoinMode joinmode;


    Visibility visibility;
    double lat;
    double lon;
    String who; // "members", "makers", "pythonistas", etc
    Photo photo;
    String timezone;
    Event nextEvent;
    Category category;
    List<Photo> photos;


    // Present in list/search entry only
    Member organizer;
    String link;
    long utcOffset;
    List<Topic> topics;
    float rating;
    int members;
}

