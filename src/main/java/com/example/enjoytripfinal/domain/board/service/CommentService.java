package com.example.enjoytripfinal.domain.board.service;

import com.example.enjoytripfinal.domain.board.dto.request.UpdateCommentRequest;
import com.example.enjoytripfinal.domain.board.dto.request.WriteCommentRequest;
import com.example.enjoytripfinal.domain.board.dto.response.CommentResponse;
import com.example.enjoytripfinal.domain.board.entity.Board;
import com.example.enjoytripfinal.domain.board.entity.Comment;
import com.example.enjoytripfinal.domain.board.mapper.BoardCommentMapper;
import com.example.enjoytripfinal.domain.board.repository.BoardRepository;
import com.example.enjoytripfinal.domain.board.repository.CommentRepository;
import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final BoardCommentMapper mapper;
    private final BoardRepository boardRepository;

    public CommentService(CommentRepository commentRepository, MemberService memberService, BoardCommentMapper mapper,
                          BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.memberService = memberService;
        this.mapper = mapper;
        this.boardRepository = boardRepository;
    }

    @Transactional
    public CommentResponse writeComment(WriteCommentRequest request) {
        Member curMember = memberService.getMemberByJwt();
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow(EntityNotFoundException::new);

        Comment comment = commentRepository.save(mapper.dtoToCommentEntity(request));

        comment.updateBoard(board);
        comment.updateMember(curMember);

        return mapper.entityToCommentResponse(comment);
    }

    @Transactional
    public CommentResponse updateComment(UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(request.getCommentId()).orElseThrow(EntityNotFoundException::new);

        comment.updateComment(request.getContent());

        return mapper.entityToCommentResponse(comment);
    }

    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }
}
