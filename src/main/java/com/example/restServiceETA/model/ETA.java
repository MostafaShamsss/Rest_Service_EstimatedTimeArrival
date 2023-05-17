package com.example.restServiceETA.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ETA
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String estimated_time_arrival;

    public void setEstimated_time_arrival(String estimated_time_arrival) {
        this.estimated_time_arrival = estimated_time_arrival;
    }
}
