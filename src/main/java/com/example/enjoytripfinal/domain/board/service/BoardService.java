package com.example.enjoytripfinal.domain.board.service;

import com.example.enjoytripfinal.domain.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


}
