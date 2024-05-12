package com.example.enjoytripfinal.domain.member.repository;

import com.example.enjoytripfinal.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    @Override
    Optional<Member> findById(UUID uuid);
}
