package com.enerionenergy.enerion_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enerionenergy.enerion_backend.entity.Bike;

public interface BikeRepository extends JpaRepository<Bike, Long> {
    
}
