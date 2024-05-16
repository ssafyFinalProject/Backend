package com.example.enjoytripfinal.domain.board.controller;

import com.example.enjoytripfinal.domain.board.dto.request.WriteCommentRequest;
import com.example.enjoytripfinal.domain.board.dto.response.CommentResponse;
import com.example.enjoytripfinal.domain.board.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
