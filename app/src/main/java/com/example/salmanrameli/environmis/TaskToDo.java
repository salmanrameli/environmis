package com.example.salmanrameli.environmis;

public class TaskToDo {
    private String location_latitude;
    private String location_longitude;
    private String todo_key;
    private String staff_id;
    private String is_done;

    public TaskToDo() {

    }

    public TaskToDo(String todo_key, String location_latitude, String location_longitude, String staff_id, String is_done) {
        this.todo_key = todo_key;
        this.location_latitude = location_latitude;
        this.location_longitude = location_longitude;
        this.staff_id = staff_id;
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

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
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

    public String getStaff_id() {
        return staff_id;
    }

    public String getIs_done() {
        return is_done;
    }
}
