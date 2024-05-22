package com.example.enjoytripfinal.domain.gpt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GptRequest {
    private String model;
    private List<Message> messages;

    public GptRequest(String model,String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        messages.add(new Message("user",prompt));
    }
}
