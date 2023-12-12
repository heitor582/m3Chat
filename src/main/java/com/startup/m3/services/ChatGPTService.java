package com.startup.m3.services;

import com.startup.m3.dto.ChatGPTRequestDto;
import com.startup.m3.dto.ChatGPTResponse;
import com.startup.m3.dto.ChatGptMessageDto;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Service
public class ChatGPTService {
    @Value("${chatgpt.token}")
    private String token;

    private final RestTemplate restTemplate;

    public ChatGPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendMessage(String message) {
        final String uri = "https://api.openai.com/v1/chat/completions";
        final ChatGPTRequestDto body = new ChatGPTRequestDto(
                "gpt-3.5-turbo", 500L,
                List.of(
                        new ChatGptMessageDto("system", "You are a business man with thousand of years making business"),
                        new ChatGptMessageDto("user", message)
                ),
                1.0
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<ChatGPTResponse> response = restTemplate.postForEntity(uri, requestEntity, ChatGPTResponse.class);
        final int choiceSize = Objects.requireNonNull(response.getBody()).choices().size();
        return response.getBody().choices().get(choiceSize-1).message().content();
    }
}
