package com.example.enjoytripfinal.domain.place.dto.request;

import com.example.enjoytripfinal.domain.place.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceRequest {
    private String name;
    private Category category;
    private String roadAddress;
}
