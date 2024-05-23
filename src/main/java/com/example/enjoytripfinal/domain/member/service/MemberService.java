package com.example.enjoytripfinal.domain.member.service;

import com.example.enjoytripfinal.domain.member.dto.request.UpdateMemberRequest;
import com.example.enjoytripfinal.domain.member.dto.response.DuplicateEmailResponse;
import com.example.enjoytripfinal.domain.member.dto.response.DuplicateNicknameResponse;
import com.example.enjoytripfinal.domain.member.dto.response.MemberResponse;
import com.example.enjoytripfinal.domain.member.entity.Member;
import com.example.enjoytripfinal.domain.member.mapper.MemberMapper;
import com.example.enjoytripfinal.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository,MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    // 닉네임 중복 검사
    public DuplicateNicknameResponse checkDuplicateNickname(String name) {
        return new DuplicateNicknameResponse(memberRepository.existsByNickName(name));
    }

    public MemberResponse getMemberResponseByNickname(String name) {
        return memberMapper.entityToDto(getMemberByNickname(name));
    }
    // 닉네임 통한 member 찾기
    public Member getMemberByNickname(String nickname) {
        return memberRepository.findByNickName(nickname).orElseThrow(EntityNotFoundException::new);
    }

    public Member getMemberByJwt() {
        return memberRepository.findById(getMemberIdValue()).orElseThrow(EntityNotFoundException::new);
    }

    private UUID getMemberIdValue() {
        return UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    // 인증된 정보를 바탕으로 현재 사용자의 정보 추출
    public MemberResponse getMemberDtoByJwt() {
        return memberMapper.entityToDto(getMemberById(getMemberIdValue()));
    }

    public Member getMemberById(UUID id) {
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    @Transactional
    public MemberResponse updateMember(UpdateMemberRequest request) {
        Member curMember = getMemberByNickname(request.getCurNickname());

        if(!checkDuplicateNickname(request.getChangeNickname()).getIsPresent()) {
            curMember.updateNickname(request.getChangeNickname());
        }

        return memberMapper.entityToDto(curMember);
    }


    public void deleteMember(UUID id) {
        memberRepository.deleteById(id);
    }

    public DuplicateEmailResponse checkDuplicateEmail(String email) {
        return new DuplicateEmailResponse(memberRepository.existsByEmail(email));
    }

    public MemberResponse findMemberByEmail(String email) {
        return memberMapper.entityToDto(memberRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new));
    }
}
