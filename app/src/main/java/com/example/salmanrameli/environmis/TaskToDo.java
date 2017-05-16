package com.example.salmanrameli.environmis;

public class TaskToDo {
    private String location_latitude;
    private String location_longitude;

    public TaskToDo() {

    }

    public TaskToDo(String location_latitude, String location_longitude) {
        this.location_latitude = location_latitude;
        this.location_longitude = location_longitude;
    }

    public void setLocation_latitude(String location_latitude) {
        this.location_latitude = location_latitude;
    }

    public void setLocation_longitude(String location_longitude) {
        this.location_longitude = location_longitude;
    }

    public String getLocation_latitude() {
        return location_latitude;
    }

    public String getLocation_longitude() {
        return location_longitude;
    }
}
