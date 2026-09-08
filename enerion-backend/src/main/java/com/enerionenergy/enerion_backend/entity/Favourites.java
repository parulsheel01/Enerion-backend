package com.enerionenergy.enerion_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bike_id", nullable = false)
    private Bike bike;

    public Favourites() {
    }

    public Favourites(User user, Bike bike) {
        this.user = user;
        this.bike = bike;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Bike getBike() {
        return bike;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
}
