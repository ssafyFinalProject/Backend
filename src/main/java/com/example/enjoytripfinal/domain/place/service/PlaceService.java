package com.example.enjoytripfinal.domain.place.service;

import com.example.enjoytripfinal.domain.place.dto.request.PlaceRequest;
import com.example.enjoytripfinal.domain.place.dto.response.PlaceResponse;
import com.example.enjoytripfinal.domain.place.entity.Category;
import com.example.enjoytripfinal.domain.place.entity.Place;
import com.example.enjoytripfinal.domain.place.mapper.PlaceMapper;
import com.example.enjoytripfinal.domain.place.repository.PlaceRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    private final PlaceMapper placeMapper;

    public PlaceService(PlaceRepository placeRepository,PlaceMapper placeMapper) {
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
    }

    @Transactional(readOnly = true)
    public List<PlaceResponse> findPlaceListByCategory(String categoryName) {
        Category category = Category.toCategory(categoryName);
        return placeRepository.findAllByCategory(category).stream().map(placeMapper::entityToResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<PlaceResponse> findPlaceListByName(String placeName) {
        return placeRepository.findAllByName(placeName).stream().map(placeMapper::entityToResponse).toList();
    }


    @Transactional(readOnly = true)
    public List<PlaceResponse> findPlaceListByRoadAddress(String roadAddress) {
        return placeRepository.findAllByRoadAddress(roadAddress).stream().map(placeMapper::entityToResponse).toList();
    }

    public List<PlaceResponse> findPlaceByDetail(PlaceRequest request) {
        return placeRepository.findAllByDetail(request.getName(),request.getCategory(),request.getRoadAddress()).stream().map(placeMapper::entityToResponse).toList();
    }

    public Place findPlaceById(UUID id) {
        return placeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}


