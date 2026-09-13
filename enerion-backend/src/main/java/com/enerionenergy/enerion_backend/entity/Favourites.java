package com.enerionenergy.enerion_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "favourites",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "bike_id"})
    }
)
public class Favourites {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long bikeId;
    public Favourites() {
    }

    public Favourites(long userId, long bikeId) {
        this.userId = userId;
        this.bikeId = bikeId;
    }

    public Long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getBikeId() {
        return bikeId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setBikeId(long bikeId) {
        this.bikeId = bikeId;
    }
}
