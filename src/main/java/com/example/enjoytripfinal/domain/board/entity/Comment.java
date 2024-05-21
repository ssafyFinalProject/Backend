package com.example.enjoytripfinal.domain.board.entity;

import com.example.enjoytripfinal.domain.member.entity.Member;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UlidCreator.getMonotonicUlid().toUuid();

    private String content;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    public Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    public Board board;

    public Comment(String content) {
        this.content = content;
        this.date = LocalDateTime.now();
    }

    public void updateComment(String content) {
        this.content = content;
    }

    public void updateBoard(Board board) {
        this.board = board;
        board.getCommentList().add(this);
    }

    public void updateMember(Member member) {
        this.member = member;
        member.getComments().add(this);
    }
}
