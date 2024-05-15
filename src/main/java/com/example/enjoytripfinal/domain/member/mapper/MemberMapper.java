package com.example.enjoytripfinal.domain.member.mapper;

import com.example.enjoytripfinal.domain.member.dto.request.SignUpRequest;
import com.example.enjoytripfinal.domain.member.dto.response.MemberResponse;
import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.member.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member dtoToAdminEntity(SignUpRequest requestDto) {
        return new Member(
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getNickname(),
                Role.ROLE_ADMIN
        );
    }

    public Member dtoToMemberEntity(SignUpRequest requestDto) {
        return new Member(
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getNickname(),
                Role.ROLE_USER
        );
    }
    public MemberResponse entityToDto(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getNickName(),
                member.getEmail(),
                member.getPassword()
        );
    }



}
