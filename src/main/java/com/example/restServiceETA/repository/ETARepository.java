package com.example.restServiceETA.repository;

import com.example.restServiceETA.model.ETA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ETARepository extends JpaRepository<ETA, Long> {
}

