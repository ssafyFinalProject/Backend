package com.example.enjoytripfinal.domain.member.entity;

import com.example.enjoytripfinal.domain.board.entity.Board;
import com.example.enjoytripfinal.domain.board.entity.Comment;
import com.example.enjoytripfinal.domain.place.entity.Place;
import com.example.enjoytripfinal.domain.plan.entity.Plan;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UlidCreator.getMonotonicUlid().toUuid();

    private String email;

    private String password;

    private String nickName;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Plan> plans = new ArrayList<>();

    public Member(String email,String password,String nickName,Role role) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.role = role;
    }

    public void updateNickname(String nickName) {
        this.nickName = nickName;
    }
}
