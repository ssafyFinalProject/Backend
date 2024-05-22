package com.example.enjoytripfinal.domain.plan.service;

import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.member.service.MemberService;
import com.example.enjoytripfinal.domain.place.entity.Place;
import com.example.enjoytripfinal.domain.place.service.PlaceService;
import com.example.enjoytripfinal.domain.plan.dto.request.MakePlanRequest;
import com.example.enjoytripfinal.domain.plan.dto.request.MakePostRequest;
import com.example.enjoytripfinal.domain.plan.dto.request.UpdatePlanRequest;
import com.example.enjoytripfinal.domain.plan.dto.request.UpdatePostRequest;
import com.example.enjoytripfinal.domain.plan.dto.response.PlanDetailResponse;
import com.example.enjoytripfinal.domain.plan.dto.response.PlanResponse;
import com.example.enjoytripfinal.domain.plan.dto.response.PostResponse;
import com.example.enjoytripfinal.domain.plan.entity.Plan;
import com.example.enjoytripfinal.domain.plan.entity.Post;
import com.example.enjoytripfinal.domain.plan.mapper.PlanMapper;
import com.example.enjoytripfinal.domain.plan.mapper.PostMapper;
import com.example.enjoytripfinal.domain.plan.repository.PlanRepository;
import com.example.enjoytripfinal.domain.plan.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public PlanDetailResponse getPlanById(UUID id) {
        return planMapper.toPlanDetailResponse(planRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public PostResponse addPost(MakePostRequest request) {
        Place place = placeService.findPlaceById(request.getPlaceId());
        Plan plan = findPlanById(request.getPlanId());
        Post post = postRepository.save(postMapper.toEntity(request,plan));
        post.updatePlan(plan);
        post.updatePlace(place);
        plan.updateImage(place.getImg());

        return postMapper.toPostResponse(post,place);
    }

    public List<PlanResponse> getMyPlanList() {
        Member curMember = memberService.getMemberByJwt();
        return curMember.getPlans().stream().map(planMapper::toPlanResponse).toList();
    }

    public Plan findPlanById(UUID id) {
        return planRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public PlanResponse updatePlan(UpdatePlanRequest request) {
        Plan plan = planRepository.findById(request.getPlanId()).orElseThrow(EntityNotFoundException::new);
        plan.updatePlan(request.getName(),request.getContent(),request.getPlanDay());

        return planMapper.toPlanResponse(plan);
    }

    @Transactional
    public PostResponse updatePost(UpdatePostRequest request) {
        Post post = postRepository.findById(request.getPostId()).orElseThrow(EntityNotFoundException::new);
        Place place = placeService.findPlaceById(request.getPlaceId());

        post.updatePost(request.getName(),request.getContent(),request.getPostDay(),place);

        return postMapper.toPostResponse(post,place);
    }

    public void deletePlan(UUID id) {
        planRepository.deleteById(id);
    }

    public void deletePost(UUID id) {
        postRepository.deleteById(id);
    }
}
