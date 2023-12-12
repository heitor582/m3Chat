package com.startup.m3.dto;

import java.util.List;

public record ChatGPTResponse(
        String id,
        String object,
        String created,
        String model,
        String system_fingerprint,
        List<Choice> choices,
        Usage usage
) {
    public record Message(
            String role,
            String content
    ) {

    }

    public record Choice(
            Long index,
            Message message,

            String finish_reason
    ) {
    }

    public record Usage(
            Long  prompt_tokens,
            Long completion_tokens,
            Long total_tokens
    ) {}

}

