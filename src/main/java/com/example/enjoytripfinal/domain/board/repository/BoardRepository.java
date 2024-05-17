package com.example.enjoytripfinal.domain.board.repository;


import com.example.enjoytripfinal.domain.board.entity.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, UUID> {
    @Query(value = "SELECT b FROM Board b JOIN FETCH b.member")
    Page<Board> findAllByMember(Pageable pageable);

    @Query("SELECT b FROM Board b " +
            "JOIN FETCH b.member " +
            "LEFT JOIN FETCH b.commentList " +
            "WHERE b.id = :id")
    Optional<Board> findBoardWithMemberAndCommentListById(@Param("id") UUID id);

    @Query(value = "SELECT b FROM Board b JOIN FETCH b.member WHERE b.id = :boardID")
    Optional<Board> findByIdWithMember(@Param("boardID") UUID boardID);
}
