package com.example.enjoytripfinal.domain.plan.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostRequest {
    private UUID postId;
    private UUID placeId;
    private String name;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd",
            timezone = "Asia/Seoul")
    private LocalDate postDay;
}
