package com.example.enjoytripfinal.domain.plan.entity;

import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.place.entity.Place;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Plan {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UlidCreator.getMonotonicUlid().toUuid();

    private String name;

    private String content;

    private LocalDateTime planDay;

    @OneToMany(mappedBy = "plan", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<Post> posts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)", name = "member_id")
    private Member member;

    public Plan(String name,String content,LocalDateTime planDay) {
        this.name = name;
        this.content = content;
        this.planDay = planDay;
    }

    public void updateMember(Member member) {
        member.getPlans().add(this);
        this.member = member;
    }

}
