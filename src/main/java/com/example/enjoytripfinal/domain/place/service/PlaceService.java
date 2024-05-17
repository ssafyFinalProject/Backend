package com.example.enjoytripfinal.domain.place.service;

import com.example.enjoytripfinal.domain.place.repository.PlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
         this.placeRepository = placeRepository;
    }
}
