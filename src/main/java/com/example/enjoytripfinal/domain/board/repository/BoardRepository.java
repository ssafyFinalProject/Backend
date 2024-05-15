package com.example.enjoytripfinal.domain.board.repository;


import com.example.enjoytripfinal.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, UUID> {

}
