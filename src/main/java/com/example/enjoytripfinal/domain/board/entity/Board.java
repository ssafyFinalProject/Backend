package com.example.enjoytripfinal.domain.board.entity;

import com.example.enjoytripfinal.domain.member.entity.Member;
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
public class Board {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UlidCreator.getMonotonicUlid().toUuid();

    private String title;

    private String content;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<Comment> commentList = new ArrayList<>();

    public void setMappingMember(Member member) {
        this.member = member;
    }
}
