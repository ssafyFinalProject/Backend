package com.example.enjoytripfinal.domain.place.repository;

import com.example.enjoytripfinal.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaceRepository extends JpaRepository<Place, UUID> {

}
