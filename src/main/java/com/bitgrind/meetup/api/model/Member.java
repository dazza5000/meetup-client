package com.bitgrind.meetup.api.model;

import java.util.List;
import java.util.Map;

public class Member {
    public Date birthday;
    public float lon;
    public String hometown;
    enum Status {
        ACTIVE;
        public String toString() {
            return name().toLowerCase();
        }

    }
    public Status status;
    public String link;
    public String state;
    public Photo photo;
    public String lang;
    public String city;
    public String country;
    public long id;

    public void setMemberId(int id) {
        this.id = id;
    }

    public long visited;
    public List<Topic> topics;
    public long joined;
    public String bio;
    public String name;
    public float lat;

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", rsvpId=" + id +
                '}';
    }

    public Map<String, Service> otherServices;


}
