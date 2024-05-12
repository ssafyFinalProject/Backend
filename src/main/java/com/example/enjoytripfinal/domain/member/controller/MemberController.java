package com.example.enjoytripfinal.domain.member.controller;

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

    @GetMapping
    public ResponseEntity<Void> getMemberByNickname(@RequestParam(name = "nickname") String nickname){
        // nickname으로 회원 정보 반환
    }

    @GetMapping("/me")
    public ResponseEntity<Void> getMemberByJwt(){
        // 자기 자신의 정보 반환
    }

    @PutMapping
    public ResponseEntity<Void> updateMember() {
        // 회원 정보 수정
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMember() {
        // 회원 정보 삭제
    }
}
