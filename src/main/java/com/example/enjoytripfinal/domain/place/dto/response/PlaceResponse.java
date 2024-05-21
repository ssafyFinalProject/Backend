package com.example.enjoytripfinal.domain.place.dto.response;

import com.example.enjoytripfinal.domain.place.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceResponse {

    private UUID placeId;

    private String name;

    private Category category;

    private String roadAddress;

    private String address;

    private Double latitude;

    private Double longitude;

    private String img;
}
