package com.example.enjoytripfinal.domain.plan.dto.response;

import com.example.enjoytripfinal.domain.place.dto.response.PlaceResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private UUID postId;
    private String name;
    private String content;
    private PlaceResponse place;
}
