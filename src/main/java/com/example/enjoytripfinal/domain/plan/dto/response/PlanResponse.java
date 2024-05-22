package com.example.enjoytripfinal.domain.plan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanResponse {
    private UUID planId;
    private String name;
    private String content;
    private LocalDate planDate;
    private String mainImage;
}
