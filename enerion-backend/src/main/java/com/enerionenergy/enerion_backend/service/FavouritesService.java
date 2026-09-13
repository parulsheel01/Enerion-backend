package com.enerionenergy.enerion_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.enerionenergy.enerion_backend.entity.Bike;
import com.enerionenergy.enerion_backend.entity.Favourites;
import com.enerionenergy.enerion_backend.repository.BikeRepository;
import com.enerionenergy.enerion_backend.repository.FavouritesRepository;
import com.enerionenergy.enerion_backend.repository.UserRepository;
@Service
public class FavouritesService {
    private final FavouritesRepository favouriteRepository;
    private final UserRepository userRepository;
    private final BikeRepository bikeRepository;

    public FavouritesService(
            FavouritesRepository favouriteRepository,
            UserRepository userRepository,
            BikeRepository bikeRepository
    ) {
        this.favouriteRepository = favouriteRepository;
        this.userRepository = userRepository;
        this.bikeRepository = bikeRepository;
    }

    // Get all favourites belonging to a user
    public List<Bike> getUserFavourites(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        List<Favourites> favourites = favouriteRepository.findByUserId(userId);

        List<Bike> bikes =  favourites.stream().map(fav -> bikeRepository.findById(fav.getBikeId()).orElse(null)).collect(Collectors.toList());
        return bikes;
    }

    // Add a bike to user's favourites
    public Favourites addFavourite(Long userId, Long bikeId) {

            // User user = userRepository.findById(userId)
            //         .orElseThrow(() -> new RuntimeException("User not found"));

            // Bike bike = bikeRepository.findById(bikeId)
            //         .orElseThrow(() -> new RuntimeException("Bike not found"));

        // Prevent duplicate favourites
        if (favouriteRepository.findByUserIdAndBikeId(userId, bikeId).isPresent()) {
            throw new RuntimeException("Bike is already in favourites");
        }

        Favourites favourites = new Favourites(userId, bikeId);
        return favouriteRepository.save(favourites);
    }

    // Remove a bike from user's favourites
    public void removeFavourite(Long userId, Long bikeId) {

        Favourites favourites = favouriteRepository
                .findByUserIdAndBikeId(userId, bikeId)
                .orElseThrow(() -> new RuntimeException("Favourite not found"));

        favouriteRepository.delete(favourites);
    }
}
