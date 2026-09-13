package com.enerionenergy.enerion_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "bikes")
public class Bike {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String brand;

    private String model;

    private String description;

    private Double price;

    @Column(name = "battery_price")
    private Double batteryPrice;

    @Column(name = "bike_range")
    private String bike_range;

    @Column(name = "image_url")
    private String imageUrl;

    public Bike() {
    }

    public Bike(String name, String description, Double price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getBikeId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Double getBatteryPrice() {
        return batteryPrice;
    }

    public String getBike_range() {
        return bike_range;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setBikeId(Long bikeId) {
        this.Id = bikeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setBatteryPrice(Double batteryPrice) {
        this.batteryPrice = batteryPrice;
    }

    public void setBike_range(String bike_range) {
        this.bike_range = bike_range;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
