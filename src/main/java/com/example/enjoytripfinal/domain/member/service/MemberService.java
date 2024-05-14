package com.example.enjoytripfinal.domain.member.service;

import com.example.enjoytripfinal.domain.member.dto.request.UpdateMemberRequestDto;
import com.example.enjoytripfinal.domain.member.dto.response.DuplicateNicknameResponse;
import com.example.enjoytripfinal.domain.member.dto.response.MemberResponseDto;
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
    public DuplicateNicknameResponse duplicateNickname(String name) {
        return new DuplicateNicknameResponse(memberRepository.existsByNickName(name));
    }

    public MemberResponseDto getMemberResponseByNickname(String name) {
        return getMemberResponse(getMemberByNickname(name));
    }
    // 닉네임 통한 member 찾기
    public Member getMemberByNickname(String nickname) {
        return memberRepository.findByNickName(nickname).orElseThrow(EntityNotFoundException::new);
    }

    public String getMemberIdValue() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    // 인증된 정보를 바탕으로 현재 사용자의 정보 추출
    public MemberResponseDto getMemberDtoByJwt() {
        String memberIdValue = getMemberIdValue();
        UUID id = UUID.fromString(memberIdValue);
        return getMemberResponse(getMemberById(id));
    }

    public Member getMemberById(UUID id) {
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    @Transactional
    public MemberResponseDto updateMember(UpdateMemberRequestDto request) {
        Member curMember = getMemberByNickname(request.getCurNickname());

        if(!duplicateNickname(request.getChangeNickname()).getIsPresent()) {
            curMember.updateNickname(request.getChangeNickname());
        }

        return memberMapper.entityToDto(curMember);
    }

    public MemberResponseDto getMemberResponse(Member member) {
        return memberMapper.entityToDto(member);
    }

    public void deleteMember(UUID id) {
        memberRepository.deleteById(id);
    }
}
