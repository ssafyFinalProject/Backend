package com.example.enjoytripfinal.domain.member.service;

import com.example.enjoytripfinal.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final MemberRepository memberRepository;

    public AuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void signUp() {

    }

    public void authenticateMember() {

    }

    public void logOut() {

    }
}
