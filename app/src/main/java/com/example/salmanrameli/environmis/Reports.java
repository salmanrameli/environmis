package com.example.salmanrameli.environmis;

class Reports {
    private String _id;
    private String location_latitude;
    private String location_longitude;
    private String date;
    private String result;

    public Reports()
    {

    }

    public Reports(String id, String location_latitude, String location_longitude, String date, String result)
    {
        this._id = id;
        this.location_latitude = location_latitude;
        this.location_longitude = location_longitude;
        this.date = date;
        this.result = result;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setLocation_latitude(String location_latitude) {
        this.location_latitude = location_latitude;
    }

    public void setLocation_longitude(String location_longitude) {
        this.location_longitude = location_longitude;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
