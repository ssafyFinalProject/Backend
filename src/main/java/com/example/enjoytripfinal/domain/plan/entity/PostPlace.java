package com.example.enjoytripfinal.domain.plan.entity;

import com.example.enjoytripfinal.domain.place.entity.Place;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostPlace {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UlidCreator.getMonotonicUlid().toUuid();

    @JoinColumn(columnDefinition = "BINARY(16)", name = "place_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;

    @JoinColumn(columnDefinition = "BINARY(16)", name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public PostPlace(Place place, Post post) {
        this.place = place;
        this.post = post;
        post.getPostPlaces().add(this);
    }
}
