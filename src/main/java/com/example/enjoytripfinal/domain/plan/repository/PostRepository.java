package com.example.enjoytripfinal.domain.plan.repository;

import com.example.enjoytripfinal.domain.plan.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
