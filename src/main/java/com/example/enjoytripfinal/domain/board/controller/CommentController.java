package com.example.enjoytripfinal.domain.board.controller;

import com.example.enjoytripfinal.domain.board.dto.request.UpdateCommentRequest;
import com.example.enjoytripfinal.domain.board.dto.request.WriteCommentRequest;
import com.example.enjoytripfinal.domain.board.dto.response.CommentResponse;
import com.example.enjoytripfinal.domain.board.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping
    public ResponseEntity<CommentResponse> writeComment(@RequestBody WriteCommentRequest request) {
        return ResponseEntity.ok(commentService.writeComment(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteComment(@RequestParam(name = "id") UUID id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<CommentResponse> updateComment(@RequestBody UpdateCommentRequest request) {
        return ResponseEntity.ok(commentService.updateComment(request));
    }
}
