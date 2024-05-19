package com.example.enjoytripfinal.domain.plan.service;

import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.member.service.MemberService;
import com.example.enjoytripfinal.domain.plan.dto.request.MakePlanRequest;
import com.example.enjoytripfinal.domain.plan.dto.response.PlanResponse;
import com.example.enjoytripfinal.domain.plan.entity.Plan;
import com.example.enjoytripfinal.domain.plan.mapper.PlanMapper;
import com.example.enjoytripfinal.domain.plan.repository.PlanRepository;
import com.example.enjoytripfinal.domain.plan.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanService {
    private final PlanRepository planRepository;
    private final PostRepository postRepository;

    private final PlanMapper planMapper;
    private final MemberService memberService;

    public PlanService(PlanRepository planRepository,
                       PostRepository postRepository,
                       PlanMapper planMapper,
                       MemberService memberService) {
        this.planRepository = planRepository;
        this.postRepository = postRepository;
        this.planMapper = planMapper;
        this.memberService = memberService;
    }

    @Transactional
    public PlanResponse makePlan(MakePlanRequest request) {
        Member curMember = memberService.getMemberByJwt();
        Plan plan = planMapper.toPlanEntity(request,curMember);
        planRepository.save(plan);

        return planMapper.toPlanResponse(plan);
    }

}
