package com.example.enjoytripfinal.domain.plan.mapper;

import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.place.entity.Place;
import com.example.enjoytripfinal.domain.plan.dto.request.MakePlanRequest;
import com.example.enjoytripfinal.domain.plan.dto.response.PlanResponse;
import com.example.enjoytripfinal.domain.plan.entity.Plan;
import com.example.enjoytripfinal.domain.plan.entity.Post;
import com.example.enjoytripfinal.domain.plan.entity.PostPlace;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PlanMapper {
    public Plan toPlanEntity(MakePlanRequest request) {
        Plan plan = new Plan(
                request.getName(),
                request.getContent(),
                request.getPlanDay()
        );
        return plan;
    }

    public PlanResponse toPlanResponse(Plan plan) {
        return new PlanResponse();
    }

}
