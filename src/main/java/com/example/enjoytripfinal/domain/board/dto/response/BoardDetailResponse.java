package com.example.enjoytripfinal.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailResponse {
    private BoardResponse board;
    private List<CommentResponse> commentList;
}
