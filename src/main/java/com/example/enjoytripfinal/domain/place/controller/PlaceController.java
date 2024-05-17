package com.example.enjoytripfinal.domain.place.controller;

import com.example.enjoytripfinal.domain.place.dto.response.PlaceResponse;
import com.example.enjoytripfinal.domain.place.repository.PlaceRepository;
import com.example.enjoytripfinal.domain.place.service.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/place")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/category")
    public ResponseEntity<List<PlaceResponse>> findPlaceListByCategory(@RequestParam("category") String category) {
        return ResponseEntity.ok(placeService.findPlaceListByCategory(category));
    }

    @GetMapping("/name")
    public ResponseEntity<List<PlaceResponse>> findPlaceListByName(@RequestParam("placeName") String placeName) {
        return ResponseEntity.ok(placeService.findPlaceListByName(placeName));
    }

    @GetMapping("/road")
    public ResponseEntity<List<PlaceResponse>> findPlaceListByRoadAddress(@RequestParam("roadAddress") String roadAddress) {
        return ResponseEntity.ok(placeService.findPlaceListByRoadAddress(roadAddress));
    }
}
