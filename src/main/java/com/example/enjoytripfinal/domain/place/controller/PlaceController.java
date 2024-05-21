package com.example.enjoytripfinal.domain.place.controller;

import com.example.enjoytripfinal.domain.place.dto.request.PickPlaceRequest;
import com.example.enjoytripfinal.domain.place.dto.request.PlaceRequest;
import com.example.enjoytripfinal.domain.place.dto.response.PlaceResponse;
import com.example.enjoytripfinal.domain.place.service.PlaceService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<PlaceResponse>> findPlaceByDetail(@RequestBody PlaceRequest request) {
        return ResponseEntity.ok(placeService.findPlaceByDetail(request));
    }

    @PostMapping("/me")
    public ResponseEntity<PlaceResponse> pickMyPlace(@RequestBody PickPlaceRequest request) {
        return ResponseEntity.ok(placeService.pickMyPlace(request));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyPlace(@RequestBody PickPlaceRequest request) {
        placeService.deleteMyPlace(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<List<PlaceResponse>> getMyPlaces( @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(placeService.getMyPickPlaces(pageable));
    }
}
