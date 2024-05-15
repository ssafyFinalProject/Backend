package com.example.enjoytripfinal.domain.member.service;

import com.example.enjoytripfinal.config.security.jwt.TokenProvider;
import com.example.enjoytripfinal.domain.member.dto.request.LoginRequest;
import com.example.enjoytripfinal.domain.member.dto.request.SignUpRequest;
import com.example.enjoytripfinal.domain.member.dto.response.AfterLoginResponse;
import com.example.enjoytripfinal.domain.member.dto.response.MemberResponse;
import com.example.enjoytripfinal.domain.member.dto.response.SignStatus;
import com.example.enjoytripfinal.domain.member.dto.response.TokenDto;
import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.member.entity.RefreshToken;
import com.example.enjoytripfinal.domain.member.mapper.MemberMapper;
import com.example.enjoytripfinal.domain.member.repository.MemberRepository;
import com.example.enjoytripfinal.domain.member.repository.RefreshTokenRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthService {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    private final MemberMapper memberMapper;

    public AuthService(
            MemberService memberService,
            MemberRepository memberRepository,
            RefreshTokenRepository refreshTokenRepository,
            TokenProvider tokenProvider,
            MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenProvider = tokenProvider;
        this.memberMapper = memberMapper;
    }

    @Transactional
    public AfterLoginResponse signUpMember(SignUpRequest request) {
        Member member = memberRepository.save(memberMapper.dtoToMemberEntity(request));
        TokenDto tokenDto = tokenProvider.makeToken(member);

        return new AfterLoginResponse(SignStatus.SIGNUP,tokenDto);
    }

    @Transactional
    public AfterLoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByEmailAndPassword(request.getEmail(),request.getPassword()).orElseThrow(EntityNotFoundException::new);

        TokenDto tokenDto = tokenProvider.makeToken(member);

        return new AfterLoginResponse(SignStatus.SIGNIN,tokenDto);
    }

    private TokenDto saveRefreshToken(String userName,TokenDto token) {

        refreshTokenRepository.save(new RefreshToken(userName,token.getRefreshToken()));
        return token;
    }

    public void logout() {
        MemberResponse curMember = memberService.getMemberDtoByJwt();
        refreshTokenRepository.deleteById(curMember.getMemberId());
    }
}
