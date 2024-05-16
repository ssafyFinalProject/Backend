package com.example.enjoytripfinal.domain.board.controller;

import com.example.enjoytripfinal.domain.board.dto.request.MakeBoardRequest;
import com.example.enjoytripfinal.domain.board.dto.request.UpdateBoardRequest;
import com.example.enjoytripfinal.domain.board.dto.response.BoardDetailResponse;
import com.example.enjoytripfinal.domain.board.dto.response.BoardResponse;
import com.example.enjoytripfinal.domain.board.dto.response.CommentResponse;
import com.example.enjoytripfinal.domain.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public ResponseEntity<Page<BoardResponse>> getBoardList(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<BoardResponse> list = boardService.getPageList(pageable);
        return ResponseEntity.ok(list);
    }

    // 보기
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDetailResponse> searchBoardById(@PathVariable("boardId") UUID boardId) {
        return ResponseEntity.ok(boardService.searchBoardById(boardId));
    }

    @PutMapping
    public ResponseEntity<BoardResponse> updateBoard(@RequestBody UpdateBoardRequest request) {
        return ResponseEntity.ok(boardService.updateBoard(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBoard(@RequestParam(name = "id") UUID id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }
}
