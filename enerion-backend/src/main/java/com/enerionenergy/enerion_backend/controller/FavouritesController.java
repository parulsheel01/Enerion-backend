package com.enerionenergy.enerion_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enerionenergy.enerion_backend.dto.FavouritesDTO;
import com.enerionenergy.enerion_backend.entity.Bike;
import com.enerionenergy.enerion_backend.entity.Favourites;
import com.enerionenergy.enerion_backend.entity.User;
import com.enerionenergy.enerion_backend.service.FavouritesService;
import com.enerionenergy.enerion_backend.service.UserService;
@RestController
@RequestMapping("/api/favourites")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class FavouritesController {
    private final FavouritesService favouriteService;
    private final UserService userService;

    public FavouritesController(FavouritesService favouriteService, UserService userService) {
        this.favouriteService = favouriteService;
        this.userService = userService;
    }

    // Get all favourites for a user
    // GET /api/favourites/user/1
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bike>> getUserFavourites(@PathVariable Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        String userEmail = authentication.getName();
        //System.out.println("Authenticated user: " + userEmail);
        User user = userService.findByEmail(userEmail);
       //System.out.println("User from DB: " + user);
        if(!user.getId().equals(userId)) {
            return ResponseEntity.status(403).build(); // Forbidden
        }
        List<Bike> favourites = favouriteService.getUserFavourites(userId);
        return ResponseEntity.ok(favourites);
    }

    // Add a bike to favourites
    // POST /api/favourites/user/1/bike/5
    @PostMapping
    public ResponseEntity<Favourites> addFavourite(@RequestBody FavouritesDTO request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userService.findByEmail(userEmail);
        return ResponseEntity.ok(favouriteService.addFavourite(user.getId(), request.getBikeId()));
    }

    // Remove a bike from favourites
    // DELETE /api/favourites/user/1/bike/5
    @DeleteMapping("/{userId}/{bikeId}")
    public ResponseEntity<Void> removeFavourite(@PathVariable Long userId, @PathVariable Long bikeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userService.findByEmail(userEmail);
        if(!user.getId().equals(userId)) {
            return ResponseEntity.status(403).build(); // Forbidden
        }
        favouriteService.removeFavourite(userId, bikeId);
        return ResponseEntity.noContent().build();
    }
}

// done so far