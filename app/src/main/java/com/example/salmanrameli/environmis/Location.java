package com.example.salmanrameli.environmis;

public class Location {
    private String location_name;
    private String location_latitude;
    private String location_longitude;
    private String location_key;

    public Location() {

    }

    public Location(String location_name, String location_latitude, String location_longitude, String location_key) {
        this.location_name = location_name;
        this.location_latitude = location_latitude;
        this.location_longitude = location_longitude;
        this.location_key = location_key;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public void setLocation_latitude(String location_latitude) {
        this.location_latitude = location_latitude;
    }

    public void setLocation_longitude(String location_longitude) {
        this.location_longitude = location_longitude;
    }

    public void setLocation_key(String location_key) {
        this.location_key = location_key;
    }

    public String getLocation_name() {
        return location_name;
    }

    public String getLocation_latitude() {
        return location_latitude;
    }

    public String getLocation_longitude() {
        return location_longitude;
    }

    public String getLocation_key() {
        return location_key;
    }
}
