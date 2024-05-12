package com.example.enjoytripfinal.domain.member.service;

import com.example.enjoytripfinal.domain.member.repository.MemberRepository;
import com.example.enjoytripfinal.domain.member.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final MemberRepository memberRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    public AuthService(MemberRepository memberRepository,RefreshTokenRepository refreshTokenRepository) {
        this.memberRepository = memberRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public void signUp() {

    }

    public void authenticateMember() {

    }

    public void logOut() {

    }
}
