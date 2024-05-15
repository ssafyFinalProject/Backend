package com.example.enjoytripfinal.domain.board.entity;

import com.example.enjoytripfinal.domain.member.entity.Member;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UlidCreator.getMonotonicUlid().toUuid();

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    public Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    public Board board;
}
