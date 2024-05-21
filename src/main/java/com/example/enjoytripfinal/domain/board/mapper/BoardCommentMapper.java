package com.example.enjoytripfinal.domain.board.mapper;

import com.example.enjoytripfinal.domain.board.dto.request.MakeBoardRequest;
import com.example.enjoytripfinal.domain.board.dto.request.WriteCommentRequest;
import com.example.enjoytripfinal.domain.board.dto.response.BoardDetailResponse;
import com.example.enjoytripfinal.domain.board.dto.response.BoardResponse;
import com.example.enjoytripfinal.domain.board.dto.response.CommentResponse;
import com.example.enjoytripfinal.domain.board.entity.Board;
import com.example.enjoytripfinal.domain.board.entity.Comment;
import com.example.enjoytripfinal.domain.member.dto.response.MemberLightResponse;
import com.example.enjoytripfinal.domain.member.entity.Member;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BoardCommentMapper {

    public Board dtoToBoardEntity(MakeBoardRequest request) {
        return new Board(
                request.getTitle(),
                request.getContent()
        );
    }

    public String dateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
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
                ),
                dateToString(board.getDate())
        );
    }
    public Comment dtoToCommentEntity(WriteCommentRequest request) {
        Comment comment = new Comment(request.getContent());
        return comment;
    }
    public CommentResponse entityToCommentResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getContent(),
                new MemberLightResponse(
                        comment.getMember().getId(),
                        comment.getMember().getNickName()
                ),
                dateToString(comment.getDate())
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
