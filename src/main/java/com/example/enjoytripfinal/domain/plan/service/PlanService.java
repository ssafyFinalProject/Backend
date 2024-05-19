package com.example.enjoytripfinal.domain.plan.service;

import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.member.service.MemberService;
import com.example.enjoytripfinal.domain.place.entity.Place;
import com.example.enjoytripfinal.domain.place.service.PlaceService;
import com.example.enjoytripfinal.domain.plan.dto.request.MakePlanRequest;
import com.example.enjoytripfinal.domain.plan.dto.request.MakePostRequest;
import com.example.enjoytripfinal.domain.plan.dto.response.PlanResponse;
import com.example.enjoytripfinal.domain.plan.entity.Plan;
import com.example.enjoytripfinal.domain.plan.entity.Post;
import com.example.enjoytripfinal.domain.plan.entity.PostPlace;
import com.example.enjoytripfinal.domain.plan.mapper.PlanMapper;
import com.example.enjoytripfinal.domain.plan.mapper.PostMapper;
import com.example.enjoytripfinal.domain.plan.repository.PlanRepository;
import com.example.enjoytripfinal.domain.plan.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PlanService {
    private final PlanRepository planRepository;
    private final PostRepository postRepository;
    private final PlaceService placeService;
    private final PlanMapper planMapper;

    private final PostMapper postMapper;
    private final MemberService memberService;


    public PlanService(PlanRepository planRepository,
                       PostRepository postRepository,
                       PlaceService placeService,
                       PlanMapper planMapper,
                       PostMapper postMapper,
                       MemberService memberService) {
        this.planRepository = planRepository;
        this.postRepository = postRepository;
        this.placeService = placeService;
        this.planMapper = planMapper;
        this.postMapper = postMapper;
        this.memberService = memberService;
    }

    @Transactional
    public PlanResponse makePlan(MakePlanRequest request) {
        Member curMember = memberService.getMemberByJwt();

        Plan plan = planRepository.save(planMapper.toPlanEntity(request));
        plan.updateMember(curMember);

        return planMapper.toPlanResponse(plan);
    }

    @Transactional
    public void addPost(MakePostRequest request) {
        Place place = placeService.findPlaceById(request.getPlaceId());
        Plan plan = findPlanById(request.getPlanId());
        Post post = postMapper.toEntity(request,plan);
        post.updatePlace(new PostPlace(place,post));
        postRepository.save(post);
    }

    public Plan findPlanById(UUID id) {
        return planRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
