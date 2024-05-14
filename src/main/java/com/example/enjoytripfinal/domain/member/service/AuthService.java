package com.example.enjoytripfinal.domain.member.service;

import com.example.enjoytripfinal.config.security.jwt.TokenProvider;
import com.example.enjoytripfinal.domain.member.dto.request.SignUpRequestDto;
import com.example.enjoytripfinal.domain.member.dto.response.AfterLoginResponse;
import com.example.enjoytripfinal.domain.member.dto.response.TokenDto;
import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.member.entity.RefreshToken;
import com.example.enjoytripfinal.domain.member.mapper.MemberMapper;
import com.example.enjoytripfinal.domain.member.repository.MemberRepository;
import com.example.enjoytripfinal.domain.member.repository.RefreshTokenRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    private final MemberMapper memberMapper;

    public AuthService(
            MemberRepository memberRepository,
            RefreshTokenRepository refreshTokenRepository,
            TokenProvider tokenProvider,
            MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenProvider = tokenProvider;
        this.memberMapper = memberMapper;
    }

    @Transactional
    public AfterLoginResponse signUpMember(SignUpRequestDto request) {
        Member member = memberRepository.save(memberMapper.dtoToMemberEntity(request));
        UsernamePasswordAuthenticationToken credit = tokenProvider.makeCredit(member);
        TokenDto tokenDto = saveRefreshToken(credit,member.getId().toString());

        return new AfterLoginResponse(tokenDto);
    }

    public TokenDto saveRefreshToken(Authentication authentication,String userName) {
        TokenDto token = tokenProvider.createToken(authentication);
        refreshTokenRepository.save(new RefreshToken(userName,token.getRefreshToken()));
        return token;
    }

    public void authenticateMember() {

    }

    public void logOut() {

    }
}
