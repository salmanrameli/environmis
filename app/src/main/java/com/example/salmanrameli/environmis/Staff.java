package com.example.salmanrameli.environmis;

public class Staff {
    private String _id;
    private String username;
    private String role;

    public Staff() {

    }

    public Staff(String id, String username, String role) {
        this._id = id;
        this.username = username;
        this.role = role;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
