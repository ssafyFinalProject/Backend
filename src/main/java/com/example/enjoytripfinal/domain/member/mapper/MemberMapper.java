package com.example.enjoytripfinal.domain.member.mapper;

import com.example.enjoytripfinal.domain.member.dto.request.SignUpRequestDto;
import com.example.enjoytripfinal.domain.member.dto.response.MemberResponseDto;
import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.member.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member dtoToAdminEntity(SignUpRequestDto requestDto) {
        return new Member(
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getNickname(),
                Role.ROLE_ADMIN
        );
    }

    public Member dtoToMemberEntity(SignUpRequestDto requestDto) {
        return new Member(
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getNickname(),
                Role.ROLE_USER
        );
    }
    public MemberResponseDto entityToDto(Member member) {
        return new  MemberResponseDto(
                member.getId(),
                member.getNickName(),
                member.getEmail(),
                member.getPassword()
        );
    }



}
