package com.example.enjoytripfinal.domain.board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MakeBoardRequest {
    private String title;
    private String content;
}
