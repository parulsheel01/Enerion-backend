package com.enerionenergy.enerion_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.enerionenergy.enerion_backend.entity.Favourites;
import com.enerionenergy.enerion_backend.service.FavouritesService;

public class FavouritesController {
    private final FavouritesService favouriteService;

    public FavouritesController(FavouritesService favouriteService) {
        this.favouriteService = favouriteService;
    }

    // Get all favourites for a user
    // GET /api/favourites/user/1
    @GetMapping("/user/{userId}")
    public List<Favourites> getUserFavourites(
            @PathVariable Long userId
    ) {
        return favouriteService.getUserFavourites(userId);
    }

    // Add a bike to favourites
    // POST /api/favourites/user/1/bike/5
    @PostMapping("/user/{userId}/bike/{bikeId}")
    public Favourites addFavourite(
            @PathVariable Long userId,
            @PathVariable Long bikeId
    ) {
        return favouriteService.addFavourite(userId, bikeId);
    }

    // Remove a bike from favourites
    // DELETE /api/favourites/user/1/bike/5
    @DeleteMapping("/user/{userId}/bike/{bikeId}")
    public void removeFavourite(
            @PathVariable Long userId,
            @PathVariable Long bikeId
    ) {
        favouriteService.removeFavourite(userId, bikeId);
    }
}
