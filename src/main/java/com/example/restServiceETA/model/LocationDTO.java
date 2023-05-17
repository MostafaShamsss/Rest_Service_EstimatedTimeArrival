package com.example.restServiceETA.model;

public class LocationDTO {
    private float driverLongitude;
    private float driverLatitude;
    private float userLongitude;
    private float userLatitude;

    public float getDriverLongitude() {
        return driverLongitude;
    }

    public void setDriverLongitude(float driverLongitude) {
        this.driverLongitude = driverLongitude;
    }

    public float getDriverLatitude() {
        return driverLatitude;
    }

    public void setDriverLatitude(float driverLatitude) {
        this.driverLatitude = driverLatitude;
    }


    public float getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(float userLongitude) {
        this.userLongitude = userLongitude;
    }

    public float getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(float userLatitude) {
        this.userLatitude = userLatitude;
    }
}