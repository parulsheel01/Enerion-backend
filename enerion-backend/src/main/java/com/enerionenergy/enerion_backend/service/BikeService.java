package com.enerionenergy.enerion_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enerionenergy.enerion_backend.entity.Bike;
import com.enerionenergy.enerion_backend.repository.BikeRepository;

@Service 
public class BikeService {
    
     private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    public Bike getBikeById(Long id) {
        return bikeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bike not found"));
    }
}
