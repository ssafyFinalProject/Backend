package com.example.enjoytripfinal.domain.member.controller;

import com.example.enjoytripfinal.domain.member.dto.request.UpdateMemberRequestDto;
import com.example.enjoytripfinal.domain.member.dto.response.DuplicateNicknameResponse;
import com.example.enjoytripfinal.domain.member.dto.response.MemberResponseDto;
import com.example.enjoytripfinal.domain.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/duplicate")
    public ResponseEntity<DuplicateNicknameResponse> duplicateNickname(@RequestParam(name = "name") String name) {

        return ResponseEntity.ok(memberService.duplicateNickname(name));
    }

    @GetMapping
    public ResponseEntity<MemberResponseDto> getMemberByNickname(@RequestParam(name = "nickname") String nickname){
        return ResponseEntity.ok(memberService.getMemberResponseByNickname(nickname));
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMemberByJwt(){
        return ResponseEntity.ok(memberService.getMemberDtoByJwt());
    }

    @PutMapping
    public ResponseEntity<MemberResponseDto> updateMember(@RequestBody UpdateMemberRequestDto request) {
        return ResponseEntity.ok(memberService.updateMember(request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMember(@RequestParam(name = "id") UUID id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }
}
