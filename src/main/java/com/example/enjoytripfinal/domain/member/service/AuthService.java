package com.example.enjoytripfinal.domain.member.service;

import com.example.enjoytripfinal.config.security.jwt.TokenProvider;
import com.example.enjoytripfinal.domain.member.repository.MemberRepository;
import com.example.enjoytripfinal.domain.member.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final TokenProvider tokenProvider;

    public AuthService(
            MemberRepository memberRepository,
            RefreshTokenRepository refreshTokenRepository,
            TokenProvider tokenProvider) {
        this.memberRepository = memberRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenProvider = tokenProvider;
    }

    public void signUp() {

    }

    public void authenticateMember() {

    }

    public void logOut() {

    }
}
