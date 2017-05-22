package com.example.salmanrameli.environmis;

public class TaskToDo {
    private String location_latitude;
    private String location_longitude;
    private String location_name;
    private String todo_key;
    private String staff_name;
    private String is_done;

    public TaskToDo() {

    }

    public TaskToDo(String todo_key, String location_latitude, String location_longitude, String location_name, String staff_name, String is_done) {
        this.todo_key = todo_key;
        this.location_latitude = location_latitude;
        this.location_longitude = location_longitude;
        this.location_name = location_name;
        this.staff_name = staff_name;
        this.is_done = is_done;
    }

    public void setTodo_key(String todo_key) {
        this.todo_key = todo_key;
    }

    public void setLocation_latitude(String location_latitude) {
        this.location_latitude = location_latitude;
    }

    public void setLocation_longitude(String location_longitude) {
        this.location_longitude = location_longitude;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public void setIs_done(String is_done) {
        this.is_done = is_done;
    }

    public String getTodo_key() {
        return todo_key;
    }

    public String getLocation_latitude() {
        return location_latitude;
    }

    public String getLocation_longitude() {
        return location_longitude;
    }

    public String getLocation_name() {
        return location_name;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public String getIs_done() {
        return is_done;
    }
}
