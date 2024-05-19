package com.example.enjoytripfinal.domain.plan.controller;

import com.example.enjoytripfinal.domain.plan.dto.request.MakePlanRequest;
import com.example.enjoytripfinal.domain.plan.dto.response.PlanResponse;
import com.example.enjoytripfinal.domain.plan.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plan")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<PlanResponse> savePlan(@RequestBody MakePlanRequest request) {
        return ResponseEntity.ok(planService.makePlan(request));
    }
}
