package com.example.enjoytripfinal.domain.plan.mapper;

import com.example.enjoytripfinal.domain.place.dto.response.PlaceResponse;
import com.example.enjoytripfinal.domain.place.entity.Place;
import com.example.enjoytripfinal.domain.plan.dto.request.MakePostRequest;
import com.example.enjoytripfinal.domain.plan.dto.response.PostResponse;
import com.example.enjoytripfinal.domain.plan.entity.Plan;
import com.example.enjoytripfinal.domain.plan.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post toEntity(MakePostRequest request, Plan plan) {
        Post post = new Post(request.getName(),request.getContent(),request.getPostDay());
        post.updatePlan(plan);
        return post;
    }

    public PostResponse toPostResponse(Post post, Place place) {
        PostResponse postResponse = new PostResponse(
                post.getId(),
                post.getName(),
                post.getContent(),
                new PlaceResponse(
                        place.getId(),
                        place.getName(),
                        place.getCategory(),
                        place.getRoadAddress(),
                        place.getAddress(),
                        place.getLatitude(),
                        place.getLongitude()
                )
        );

        return postResponse;
    }
}
