package com.example.enjoytripfinal.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class LoginRequestDto {
    private String email;
    private String password;
}