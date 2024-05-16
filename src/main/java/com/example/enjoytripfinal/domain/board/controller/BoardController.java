package com.example.enjoytripfinal.domain.board.controller;

import com.example.enjoytripfinal.domain.board.dto.request.MakeBoardRequest;
import com.example.enjoytripfinal.domain.board.dto.response.BoardDetailResponse;
import com.example.enjoytripfinal.domain.board.dto.response.BoardResponse;
import com.example.enjoytripfinal.domain.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardResponse> makeBoard(@RequestBody MakeBoardRequest request) {
        return ResponseEntity.ok(boardService.makeBoard(request));
    }

    @GetMapping
    public ResponseEntity<Page<BoardDetailResponse>> getBoardList(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<BoardDetailResponse> list = boardService.getPageList(pageable);
        return ResponseEntity.ok(list);
    }


}
