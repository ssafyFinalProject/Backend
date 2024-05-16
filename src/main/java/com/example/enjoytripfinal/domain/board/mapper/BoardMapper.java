package com.example.enjoytripfinal.domain.board.mapper;

import com.example.enjoytripfinal.domain.board.dto.request.MakeBoardRequest;
import com.example.enjoytripfinal.domain.board.dto.response.BoardDetailResponse;
import com.example.enjoytripfinal.domain.board.dto.response.BoardResponse;
import com.example.enjoytripfinal.domain.board.dto.response.CommentResponse;
import com.example.enjoytripfinal.domain.board.entity.Board;
import com.example.enjoytripfinal.domain.board.entity.Comment;
import com.example.enjoytripfinal.domain.member.dto.response.MemberLightResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BoardMapper {

    public Board dtoToBoardEntity(MakeBoardRequest request) {
        return new Board(
                request.getTitle(),
                request.getContent()
        );
    }

    public BoardResponse entityToResponse(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getView(),
                new MemberLightResponse(
                        board.getMember().getId(),
                        board.getMember().getNickName()
                )
        );
    }

    public CommentResponse entityToCommentResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getContent(),
                new MemberLightResponse(
                        comment.getMember().getId(),
                        comment.getMember().getNickName()
                )
        );
    }

    public List<CommentResponse> toCommentResponseList(List<Comment> comments) {
        return comments.stream().map(this::entityToCommentResponse).collect(Collectors.toList());
    }

    public BoardDetailResponse entityToDetailResponse(Board board) {
        return new BoardDetailResponse(
                entityToResponse(board),
                toCommentResponseList(board.getCommentList())
        );
    }
}
