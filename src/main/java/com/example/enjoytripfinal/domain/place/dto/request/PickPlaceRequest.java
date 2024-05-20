package com.example.enjoytripfinal.domain.place.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PickPlaceRequest {
    private UUID placeId;
}
