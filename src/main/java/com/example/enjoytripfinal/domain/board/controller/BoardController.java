package com.example.enjoytripfinal.domain.board.controller;

import com.example.enjoytripfinal.domain.board.service.BoardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

}
