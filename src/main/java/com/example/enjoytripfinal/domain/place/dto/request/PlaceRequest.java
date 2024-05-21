package com.example.enjoytripfinal.domain.place.dto.request;

import com.example.enjoytripfinal.domain.place.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceRequest {
    private String name;
    private Category category;
    private String roadAddress;
}
