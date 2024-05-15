package com.example.enjoytripfinal.domain.board.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class BoardResponse {
    private UUID boardId;
    private String title;
    private String content;
}
