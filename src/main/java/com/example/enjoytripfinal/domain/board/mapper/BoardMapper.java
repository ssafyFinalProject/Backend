package com.example.enjoytripfinal.domain.board.mapper;

import com.example.enjoytripfinal.domain.board.dto.request.MakeBoardRequest;
import com.example.enjoytripfinal.domain.board.dto.response.BoardResponse;
import com.example.enjoytripfinal.domain.board.entity.Board;
import com.example.enjoytripfinal.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

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
                board.getView()
        );
    }
}
