package com.example.enjoytripfinal.domain.plan.mapper;

import com.example.enjoytripfinal.domain.place.dto.response.PlaceResponse;
import com.example.enjoytripfinal.domain.plan.dto.request.MakePlanRequest;
import com.example.enjoytripfinal.domain.plan.dto.response.PlanDetailResponse;
import com.example.enjoytripfinal.domain.plan.dto.response.PlanResponse;
import com.example.enjoytripfinal.domain.plan.dto.response.PostResponse;
import com.example.enjoytripfinal.domain.plan.entity.Plan;
import com.example.enjoytripfinal.domain.plan.entity.Post;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

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

    public PlanDetailResponse toPlanDetailResponse(Plan plan) {
        ArrayList<PostResponse> posts = new ArrayList<>();
        for(Post post : plan.getPosts()) {
            posts.add(new PostResponse(
                    post.getId(),
                    post.getName(),
                    post.getContent(),
                    new PlaceResponse(
                            post.getPlace().getName(),
                            post.getPlace().getCategory(),
                            post.getPlace().getRoadAddress(),
                            post.getPlace().getAddress(),
                            post.getPlace().getLatitude(),
                            post.getPlace().getLongitude()
                    )
            ));
        }

        return new PlanDetailResponse(
                toPlanResponse(plan),
                posts
        );
    }

    public PlanResponse toPlanResponse(Plan plan) {
        return new PlanResponse();
    }

}
