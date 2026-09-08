package com.enerionenergy.enerion_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enerionenergy.enerion_backend.entity.Favourites;

public interface FavouritesRepository extends JpaRepository<Favourites, Long> {

    List<Favourites> findByUserId(Long userId);

    void deleteByUserIdAndBikeId(Long userId, Long bikeId);

    Optional<Favourites> findByUserIdAndBikeId(Long userId, Long bikeId);


}
