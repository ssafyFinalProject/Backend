package com.example.enjoytripfinal.domain.member.controller;

import com.example.enjoytripfinal.domain.member.dto.request.UpdateMemberRequest;
import com.example.enjoytripfinal.domain.member.dto.response.DuplicateEmailResponse;
import com.example.enjoytripfinal.domain.member.dto.response.DuplicateNicknameResponse;
import com.example.enjoytripfinal.domain.member.dto.response.MemberResponse;
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

    @GetMapping("/duplicate/nickname")
    public ResponseEntity<DuplicateNicknameResponse> checkDuplicateNickname(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(memberService.checkDuplicateNickname(name));
    }

    @GetMapping("/duplicate/email")
    public ResponseEntity<DuplicateEmailResponse> checkDuplicateEmail(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok(memberService.checkDuplicateEmail(email));
    }


    @GetMapping
    public ResponseEntity<MemberResponse> getMemberByNickname(@RequestParam(name = "nickname") String nickname){
        return ResponseEntity.ok(memberService.getMemberResponseByNickname(nickname));
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponse> getMemberByJwt(){
        return ResponseEntity.ok(memberService.getMemberDtoByJwt());
    }

    @PutMapping
    public ResponseEntity<MemberResponse> updateMember(@RequestBody UpdateMemberRequest request) {
        return ResponseEntity.ok(memberService.updateMember(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable(name = "id") UUID id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public ResponseEntity<MemberResponse> findMember(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok(memberService.findMemberByEmail(email));
    }
}
