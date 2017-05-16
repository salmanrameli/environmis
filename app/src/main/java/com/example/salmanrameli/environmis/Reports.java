package com.example.salmanrameli.environmis;

class Reports {
    private String staff_id;
    private String location_latitude;
    private String location_longitude;
    private String measurement_date;
    private String measurement_result;

    public Reports()
    {

    }

    public Reports(String staff_id, String location_latitude, String location_longitude, String measurement_date, String measurement_result)
    {
        this.staff_id = staff_id;
        this.location_latitude = location_latitude;
        this.location_longitude = location_longitude;
        this.measurement_date = measurement_date;
        this.measurement_result = measurement_result;
    }

    public void set_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public void setLocation_latitude(String location_latitude) {
        this.location_latitude = location_latitude;
    }

    public void setLocation_longitude(String location_longitude) {
        this.location_longitude = location_longitude;
    }

    public void setDate(String date) {
        this.measurement_date = date;
    }

    public void setResult(String result) {
        this.measurement_result = result;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public String getLocation_latitude() {
        return location_latitude;
    }

    public String getLocation_longitude() {
        return location_longitude;
    }

    public String getMeasurement_date() {
        return measurement_date;
    }

    public String getMeasurement_result() {
        return measurement_result;
    }
}
