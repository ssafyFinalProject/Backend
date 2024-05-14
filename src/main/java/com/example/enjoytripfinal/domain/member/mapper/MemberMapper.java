package com.example.enjoytripfinal.domain.member.mapper;

import com.example.enjoytripfinal.domain.member.dto.response.MemberResponseDto;
import com.example.enjoytripfinal.domain.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberResponseDto entityToDto(Member member) {
        return new  MemberResponseDto(
                member.getId(),
                member.getNickName(),
                member.getEmail(),
                member.getPassword()
        );
    }



}
