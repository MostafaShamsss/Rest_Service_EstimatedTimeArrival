package com.example.restServiceETA.controller;


import com.example.restServiceETA.model.ETA;
import com.example.restServiceETA.model.LocationDTO;
import com.example.restServiceETA.repository.ETARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class ETAController
{
    @Autowired
    private ETARepository etaRepository;

    @PostMapping("/ETA")
    public ResponseEntity<ETA> getEstimate(@RequestBody LocationDTO location) {
        double driverLocationLat = location.getDriverLatitude();
        double driverLocationLong = location.getDriverLongitude();
        double userLocationLat = location.getUserLatitude();
        double userLocationLong = location.getUserLongitude();

        float distance = calculateDistance(driverLocationLat, driverLocationLong, userLocationLat, userLocationLong);


        ETA eta = new ETA();
        eta.setEstimated_time_arrival("15 minutes");

        ETA savedETA = etaRepository.save(eta);

        return ResponseEntity.ok(savedETA);
    }


    private float calculateDistance(double driverLat, double driverLong, double userLong, double userLat)
    {
        double dist = Math.sqrt(Math.pow((driverLong - userLong), 2) + Math.pow((driverLat - userLat), 2));

        return (float )dist;

    }
}



