package com.example.enjoytripfinal.domain.board.service;

import com.example.enjoytripfinal.domain.board.dto.request.MakeBoardRequest;
import com.example.enjoytripfinal.domain.board.dto.response.BoardResponse;
import com.example.enjoytripfinal.domain.board.entity.Board;
import com.example.enjoytripfinal.domain.board.mapper.BoardMapper;
import com.example.enjoytripfinal.domain.board.repository.BoardRepository;
import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;
    private final MemberService memberService;

    public BoardService(BoardRepository boardRepository, BoardMapper boardMapper, MemberService memberService) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
        this.memberService = memberService;
    }

    @Transactional
    public BoardResponse makeBoard(MakeBoardRequest request) {
        Board board = boardMapper.dtoToBoardEntity(request);
        Member curMember = memberService.getMemberByJwt();
        board.setMappingMember(curMember);
        boardRepository.save(board);

        return boardMapper.entityToResponse(board);
    }

}
