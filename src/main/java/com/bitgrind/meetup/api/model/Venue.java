package com.bitgrind.meetup.api.model;

public class Venue {
    enum Visibility {
        MEMBERS,
        PUBLIC;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public int id;
    public boolean repinned;
    public String name;
    public String address1;
    public String address2;
    public String address3;
    public String city;
    public String state;
    public String zip;
    public String country;
    public String email;
    public double lat;
    public double lon;
    public String phone;
    public String venueUrl;
    public double rating;
    public int rating_count;
}
