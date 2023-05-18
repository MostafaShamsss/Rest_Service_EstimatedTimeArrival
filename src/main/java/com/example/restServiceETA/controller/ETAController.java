package com.example.restServiceETA.controller;

import com.example.restServiceETA.model.LocationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@RestController
public class ETAController {


    @PostMapping("/ETA")
    public ResponseEntity<String> getEstimate(@RequestBody LocationDTO location) {
        try {
            double driverLocationLat = location.getDriverLatitude();
            double driverLocationLong = location.getDriverLongitude();
            double userLocationLat = location.getUserLatitude();
            double userLocationLong = location.getUserLongitude();

            float distance = calculateDistance(driverLocationLat, driverLocationLong, userLocationLat, userLocationLong);
            //time = distance / speed       ||       speed = 60km/hr
            float speed = 60;
            float time = (distance * 1000) / (speed * 60);
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = currentTime.format(formatter);
            LocalTime timeFormatted = LocalTime.parse(formattedTime, formatter);
            float minutes = timeFormatted.getMinute();
            float minuteSummation = time + minutes;

            // Calculate the updated hours and minutes
            int updatedHours = (int) (minuteSummation / 60);
            int updatedMinutes = (int) (minuteSummation % 60);

            // Increment the hours and adjust minutes if necessary
            int totalHours = timeFormatted.getHour() + updatedHours;
            int totalMinutes = timeFormatted.getMinute() + updatedMinutes;

            // Handle case when minutes exceed 60
            if (totalMinutes >= 60) {
                totalHours += totalMinutes / 60;
                totalMinutes = totalMinutes % 60;
            }

            // Handle case when hours exceed 24
            if (totalHours >= 24) {
                totalHours = totalHours % 24;
            }

            // Format the updated time
            String updatedTime = String.format("%02d:%02d", totalHours, totalMinutes);

            // Return the updated time as JSON
            return ResponseEntity.ok("{\"estimatedTime\": \"" + updatedTime + "\"}");
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("{\"error\": \"" + errorMessage + "\"}");
        }
    }


    private float calculateDistance(double driverLat, double driverLong, double userLong, double userLat) {
        double dist = Math.sqrt(Math.pow((driverLong - userLong), 2) + Math.pow((driverLat - userLat), 2));

        return (float) dist;

    }
}



