package com.example.enjoytripfinal.domain.gpt.controller;

import com.example.enjoytripfinal.domain.gpt.dto.GptRequest;
import com.example.enjoytripfinal.domain.gpt.dto.GptResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/gpt")
public class GptController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "prompt") String prompt) {
        GptRequest request = new GptRequest(model,prompt);
        GptResponse response = restTemplate.postForObject(apiURL,request, GptResponse.class);
        log.info("Response : "+response.getChoices().get(0).getMessage().getContent());
        return response.getChoices().get(0).getMessage().getContent();
    }
}
