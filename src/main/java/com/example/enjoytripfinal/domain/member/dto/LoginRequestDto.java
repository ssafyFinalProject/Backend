package com.example.enjoytripfinal.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class LoginRequestDto {
    private String email;
    private String password;
}
