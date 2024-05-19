package com.example.enjoytripfinal.domain.plan.entity;

import com.example.enjoytripfinal.domain.place.entity.Place;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UlidCreator.getMonotonicUlid().toUuid();
    private String name;
    private String content;

    @JoinColumn(columnDefinition = "BINARY(16)", name = "plan_id")
    @ManyToOne
    private Plan plan;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<PostPlace> postPlaces = new ArrayList<>();

    public Post(String name,String content) {
        this.name = name;
        this.content = content;
    }

    public void updatePlan(Plan plan) {
        this.plan = plan;
        plan.getPosts().add(this);
    }
}
