package com.enerionenergy.enerion_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enerionenergy.enerion_backend.entity.Bike;
import com.enerionenergy.enerion_backend.service.BikeService;

@RestController
@RequestMapping("/api/bikes")
@CrossOrigin(origins = "http://localhost:5173")
public class BikeController {
        private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping
    public List<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }

    @GetMapping("/{id}")
    public Bike getBikeById(@PathVariable Long id) {
        return bikeService.getBikeById(id);
    }
}
