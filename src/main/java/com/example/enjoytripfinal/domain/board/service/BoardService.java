package com.example.enjoytripfinal.domain.board.service;

import com.example.enjoytripfinal.domain.board.dto.request.MakeBoardRequest;
import com.example.enjoytripfinal.domain.board.dto.response.BoardDetailResponse;
import com.example.enjoytripfinal.domain.board.dto.response.BoardResponse;
import com.example.enjoytripfinal.domain.board.entity.Board;
import com.example.enjoytripfinal.domain.board.mapper.BoardMapper;
import com.example.enjoytripfinal.domain.board.repository.BoardRepository;
import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public Page<BoardDetailResponse> getPageList(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAllByMemberAndCommentList(pageable);

        List<BoardDetailResponse> list = boardPage.getContent()
                .stream().map(boardMapper::entityToDetailResponse).collect(Collectors.toList());

        return new PageImpl<>(list,pageable, boardPage.getTotalElements());
    }

}
