package com.startup.m3.dto;

import java.util.List;

public record ChatGPTRequestDto(
        String model,
        Long max_tokens,
        List<ChatGptMessageDto> messages,
        Double temperature
) {
}
