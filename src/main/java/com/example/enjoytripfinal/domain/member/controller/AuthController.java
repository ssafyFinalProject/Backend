package com.example.enjoytripfinal.domain.member.controller;

import com.example.enjoytripfinal.domain.member.dto.request.LoginRequestDto;
import com.example.enjoytripfinal.domain.member.dto.request.SignUpRequestDto;
import com.example.enjoytripfinal.domain.member.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequestDto request) {
        // 회원 가입
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequestDto request) {
        // 로그인
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(){
        // 로그아웃
    }
}
