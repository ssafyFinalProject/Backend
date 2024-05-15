package com.example.enjoytripfinal.domain.member.entity;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
