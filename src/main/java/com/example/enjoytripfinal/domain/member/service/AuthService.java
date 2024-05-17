package com.example.enjoytripfinal.domain.member.service;

import com.example.enjoytripfinal.config.security.jwt.TokenProvider;
import com.example.enjoytripfinal.domain.member.dto.request.LoginRequest;
import com.example.enjoytripfinal.domain.member.dto.request.SignUpRequest;
import com.example.enjoytripfinal.domain.member.dto.request.TokenRequest;
import com.example.enjoytripfinal.domain.member.dto.response.AfterLoginResponse;
import com.example.enjoytripfinal.domain.member.dto.response.MemberResponse;
import com.example.enjoytripfinal.domain.member.dto.response.SignStatus;
import com.example.enjoytripfinal.domain.member.dto.response.TokenDto;
import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.member.entity.RefreshToken;
import com.example.enjoytripfinal.domain.member.mapper.MemberMapper;
import com.example.enjoytripfinal.domain.member.repository.MemberRepository;
import com.example.enjoytripfinal.domain.member.repository.RefreshTokenRepository;
import com.example.enjoytripfinal.global.AuthorityException;
import com.example.enjoytripfinal.global.RefreshTokenInfoMismatchException;
import com.example.enjoytripfinal.global.RefreshTokenValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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
        saveRefreshToken(member.getId().toString(),tokenDto);

        return new AfterLoginResponse(SignStatus.SIGNUP,tokenDto);
    }

    @Transactional
    public AfterLoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByEmailAndPassword(request.getEmail(),request.getPassword()).orElseThrow(EntityNotFoundException::new);

        TokenDto tokenDto = tokenProvider.makeToken(member);
        saveRefreshToken(member.getId().toString(),tokenDto);

        return new AfterLoginResponse(SignStatus.SIGNIN,tokenDto);
    }

    private void saveRefreshToken(String userName,TokenDto token) {
        refreshTokenRepository.save(new RefreshToken(userName,token.getRefreshToken()));
    }

    public TokenDto renewalAccessToken(TokenRequest request) {
        final String refreshToken = request.getRefreshToken();

        if(!tokenProvider.validateToken(refreshToken)) {
            throw new RefreshTokenValidationException();
        }

        Optional<Authentication> authentication =
                tokenProvider.getAuthentication(request.getAccessToken());

        return authentication.map(auth -> new TokenDto(makeAccessToken(auth,refreshToken),refreshToken)).orElseThrow(AuthorityException::new);
    }

    public String makeAccessToken(final Authentication auth, final String refreshToken) {
        String userInfo = auth.getName();
        if(!getRefreshTokenValue(userInfo).equals(refreshToken)) {
            throw new RefreshTokenInfoMismatchException();
        }

        return tokenProvider.createAccessToken(auth);
    }

    public String getRefreshTokenValue(String tokenKey) {
        return refreshTokenRepository
                .findByTokenKey(tokenKey)
                .orElseThrow(EntityNotFoundException::new)
                .getTokenValue();
    }

    public void logout() {
        MemberResponse curMember = memberService.getMemberDtoByJwt();
        refreshTokenRepository.deleteById(curMember.getMemberId());
    }
}
