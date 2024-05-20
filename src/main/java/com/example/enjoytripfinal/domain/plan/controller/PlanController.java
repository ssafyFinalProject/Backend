package com.example.enjoytripfinal.domain.plan.controller;

import com.example.enjoytripfinal.domain.plan.dto.request.MakePlanRequest;
import com.example.enjoytripfinal.domain.plan.dto.request.MakePostRequest;
import com.example.enjoytripfinal.domain.plan.dto.request.UpdatePlanRequest;
import com.example.enjoytripfinal.domain.plan.dto.request.UpdatePostRequest;
import com.example.enjoytripfinal.domain.plan.dto.response.PlanDetailResponse;
import com.example.enjoytripfinal.domain.plan.dto.response.PlanResponse;
import com.example.enjoytripfinal.domain.plan.dto.response.PostResponse;
import com.example.enjoytripfinal.domain.plan.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping("/post")
    public  ResponseEntity<PostResponse> addPost(@RequestBody MakePostRequest request) {
        return ResponseEntity.ok(planService.addPost(request));
    }

    @GetMapping
    public ResponseEntity<PlanDetailResponse> getPlanById(@RequestParam("id") UUID id){
        return ResponseEntity.ok(planService.getPlanById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<List<PlanResponse>> getMyPlanList() {
        return ResponseEntity.ok(planService.getMyPlanList());
    }

    @PutMapping
    public ResponseEntity<PlanResponse> updatePlan(@RequestBody UpdatePlanRequest request) {
        return ResponseEntity.ok(planService.updatePlan(request));
    }

    @PutMapping("/post")
    public ResponseEntity<PostResponse> updatePost(@RequestBody UpdatePostRequest request) {
        return ResponseEntity.ok(planService.updatePost(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePlan(@RequestParam UUID id) {
        planService.deletePlan(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/post")
    public ResponseEntity<Void> deletePost(@RequestParam UUID id) {
        planService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}
