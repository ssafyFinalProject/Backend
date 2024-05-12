package com.example.enjoytripfinal.domain.member.service;

import com.example.enjoytripfinal.domain.member.mapper.MemberMapper;
import com.example.enjoytripfinal.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository,MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }
}
