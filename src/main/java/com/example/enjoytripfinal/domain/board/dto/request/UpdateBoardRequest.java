package com.example.enjoytripfinal.domain.board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class UpdateBoardRequest {
    private UUID boardId;
    private String title;
    private String content;
}
