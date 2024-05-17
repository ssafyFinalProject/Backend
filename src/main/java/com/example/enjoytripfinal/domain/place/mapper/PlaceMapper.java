package com.example.enjoytripfinal.domain.place.mapper;

import com.example.enjoytripfinal.domain.place.dto.response.PlaceResponse;
import com.example.enjoytripfinal.domain.place.entity.Place;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper {
    public PlaceResponse entityToResponse(Place place) {
        return new PlaceResponse(
                place.getName(),
                place.getCategory(),
                place.getRoadAddress(),
                place.getAddress(),
                place.getLatitude(),
                place.getLongitude()
        );
    }
}
