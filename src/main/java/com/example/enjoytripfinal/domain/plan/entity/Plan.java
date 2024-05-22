package com.example.enjoytripfinal.domain.plan.entity;

import com.example.enjoytripfinal.domain.member.entity.Member;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
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
    private LocalDate planDay;
    private String mainImage;

    @OneToMany(mappedBy = "plan", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    @BatchSize(size = 10)
    private List<Post> posts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)", name = "member_id")
    private Member member;

    public Plan(String name, String content, LocalDate planDay) {
        this.name = name;
        this.content = content;
        this.planDay = planDay;
        this.mainImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_3quuwjtFK5N03p_uTdpEK-_dnCKxvsN7dQ&s";
    }

    public void updatePlan(String name, String content, LocalDate planDay) {
        this.name = name;
        this.content = content;
        this.planDay = planDay;
    }

    public void updateMember(Member member) {
        member.getPlans().add(this);
        this.member = member;
    }

    public void updateImage(String mainImage) {
        this.mainImage = mainImage;
    }

}
