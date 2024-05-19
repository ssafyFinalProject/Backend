package com.example.enjoytripfinal.domain.plan.mapper;

import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.plan.dto.request.MakePlanRequest;
import com.example.enjoytripfinal.domain.plan.dto.response.PlanResponse;
import com.example.enjoytripfinal.domain.plan.entity.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {
    public Plan toPlanEntity(MakePlanRequest request, Member member) {
        Plan plan = new Plan(
                request.getName(),
                request.getContent(),
                request.getPlanDay()
        );

        plan.updateMember(member);

        return plan;
    }

    public PlanResponse toPlanResponse(Plan plan) {
        return new PlanResponse();
    }
}
