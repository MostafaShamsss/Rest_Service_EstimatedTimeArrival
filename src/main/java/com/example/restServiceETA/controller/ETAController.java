package com.example.restServiceETA.controller;

import com.example.restServiceETA.model.LocationDTO;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@RestController
public class ETAController
{


    @PostMapping("/ETA")
    public String getEstimate(@RequestBody LocationDTO location) {
        double driverLocationLat = location.getDriverLatitude();
        double driverLocationLong = location.getDriverLongitude();
        double userLocationLat = location.getUserLatitude();
        double userLocationLong = location.getUserLongitude();

        float distance = calculateDistance(driverLocationLat, driverLocationLong, userLocationLat, userLocationLong);
        //time = distance / speed       ||       speed = 60km/hr
        float speed = 60;
        float time = (distance*1000)/(speed*60);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = currentTime.format(formatter);
        LocalTime timeFormatted = LocalTime.parse(formattedTime, formatter);
        float minutes = timeFormatted.getMinute();
        float minuteSummation = time + minutes;


        return (formattedTime.substring(4) + String.valueOf(minuteSummation));
    }


    private float calculateDistance(double driverLat, double driverLong, double userLong, double userLat)
    {
        double dist = Math.sqrt(Math.pow((driverLong - userLong), 2) + Math.pow((driverLat - userLat), 2));

        return (float )dist;

    }
}



