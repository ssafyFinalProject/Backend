package com.example.enjoytripfinal.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberRequestDto {
    private String curNickname;
    private String changeNickname;
}
