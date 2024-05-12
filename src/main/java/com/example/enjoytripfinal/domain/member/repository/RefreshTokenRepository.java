package com.example.enjoytripfinal.domain.member.repository;

import com.example.enjoytripfinal.domain.member.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
}
