package com.example.enjoytripfinal.domain.member.controller;

import com.example.enjoytripfinal.domain.member.dto.request.UpdateMemberRequestDto;
import com.example.enjoytripfinal.domain.member.dto.response.DuplicateNicknameResponse;
import com.example.enjoytripfinal.domain.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/duplicate/{name}")
    public ResponseEntity<DuplicateNicknameResponse> duplicateNickname(@PathVariable String name) {
        // 닉네임 중복 검사
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Void> getMemberByNickname(@RequestParam(name = "nickname") String nickname){
        // nickname으로 회원 정보 반환
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<Void> getMemberByJwt(){
        // 자기 자신의 정보 반환
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateMember(@RequestBody UpdateMemberRequestDto request) {
        // 회원 정보 수정
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMember() {
        // 회원 정보 삭제
        return ResponseEntity.ok().build();
    }
}
