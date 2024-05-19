package com.example.enjoytripfinal.domain.plan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MakePostRequest {
    private UUID planId;
    private UUID placeId;
    private String name;
    private String content;
}
