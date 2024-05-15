package com.example.enjoytripfinal.domain.member.controller;

import com.example.enjoytripfinal.domain.member.dto.request.LoginRequest;
import com.example.enjoytripfinal.domain.member.dto.request.SignUpRequest;
import com.example.enjoytripfinal.domain.member.dto.request.TokenRequest;
import com.example.enjoytripfinal.domain.member.dto.response.AfterLoginResponse;
import com.example.enjoytripfinal.domain.member.dto.response.TokenDto;
import com.example.enjoytripfinal.domain.member.service.AuthService;
import jakarta.validation.Valid;
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
    public ResponseEntity<AfterLoginResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.signUpMember(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AfterLoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(){
        authService.logout();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> authorize(@RequestBody @Valid TokenRequest tokenRequest) {
        // 리프레시 토큰으로 토큰 갱신
        return ResponseEntity.ok().build();
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validate() {
        // 해당 토큰으로 접속했을 때 올바른지 확₩
        return ResponseEntity.noContent().build();
    }
}
