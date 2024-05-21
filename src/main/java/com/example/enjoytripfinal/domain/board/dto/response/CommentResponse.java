package com.example.enjoytripfinal.domain.board.dto.response;

import com.example.enjoytripfinal.domain.member.dto.response.MemberLightResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private UUID commentId;
    private UUID boardId;
    private String content;
    private MemberLightResponse member;
    private String date;
}
