package com.example.enjoytripfinal.domain.place.repository;

import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.place.entity.Place;
import com.example.enjoytripfinal.domain.place.entity.PlaceMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PlaceMemberRepository extends JpaRepository<PlaceMember, UUID> {
    Optional<PlaceMember> findByMemberAndPlace(Member member, Place place);

    @Query(value = "SELECT pm FROM PlaceMember pm JOIN FETCH pm.place WHERE pm.member.id = :memberID")
    Page<PlaceMember> findAllByMember(Pageable pageable, @Param("memberID") UUID memberID);
}
