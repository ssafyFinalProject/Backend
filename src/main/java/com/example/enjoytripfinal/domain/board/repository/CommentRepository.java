package com.example.enjoytripfinal.domain.board.repository;

import com.example.enjoytripfinal.domain.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

}
