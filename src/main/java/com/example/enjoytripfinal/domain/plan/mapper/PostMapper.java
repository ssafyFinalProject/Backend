package com.example.enjoytripfinal.domain.plan.mapper;

import com.example.enjoytripfinal.domain.plan.dto.request.MakePostRequest;
import com.example.enjoytripfinal.domain.plan.entity.Plan;
import com.example.enjoytripfinal.domain.plan.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post toEntity(MakePostRequest request, Plan plan) {
        Post post = new Post(request.getName(),request.getContent());
        post.updatePlan(plan);

        return post;
    }
}
