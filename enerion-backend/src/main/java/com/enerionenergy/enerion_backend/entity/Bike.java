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
    private Long bikeId;

    private String name;

    private String description;

    private Double price;

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
        return bikeId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
