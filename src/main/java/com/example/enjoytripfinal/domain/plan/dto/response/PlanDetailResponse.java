package com.example.enjoytripfinal.domain.plan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanDetailResponse {
    private PlanResponse plan;
    private List<PostResponse> posts;
}
