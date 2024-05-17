package com.example.enjoytripfinal.domain.board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class UpdateCommentRequest {
    private UUID commentId;
    private String content;
}
