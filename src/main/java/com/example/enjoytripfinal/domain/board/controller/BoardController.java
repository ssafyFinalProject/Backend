package com.example.enjoytripfinal.domain.board.controller;

import com.example.enjoytripfinal.domain.board.dto.request.MakeBoardRequest;
import com.example.enjoytripfinal.domain.board.dto.request.UpdateBoardRequest;
import com.example.enjoytripfinal.domain.board.dto.response.BoardCount;
import com.example.enjoytripfinal.domain.board.dto.response.BoardDetailResponse;
import com.example.enjoytripfinal.domain.board.dto.response.BoardResponse;
import com.example.enjoytripfinal.domain.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        Page<BoardResponse> list = boardService.getBoardPage(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("page/{pageNum}")
    public ResponseEntity<List<BoardResponse>> selectBoardPage(@PathVariable("pageNum") Integer pageNum) {
        return ResponseEntity.ok(boardService.selectBoardPage(pageNum));
    }

    @GetMapping("/count")
    public ResponseEntity<BoardCount> getBoardCount() {
        return ResponseEntity.ok(boardService.getBoardCount());
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

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable(name = "boardId") UUID id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }
}
