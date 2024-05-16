package com.example.enjoytripfinal.domain.board.repository;


import com.example.enjoytripfinal.domain.board.entity.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, UUID> {
    @Query(value = "SELECT b FROM Board b JOIN FETCH b.member")
    Page<Board> findAllByMemberAndCommentList(Pageable pageable);
}
