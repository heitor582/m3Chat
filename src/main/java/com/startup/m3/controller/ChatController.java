package com.startup.m3.controller;

import com.startup.m3.dto.ChatMessage;
import com.startup.m3.services.ChatService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(final ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<ChatMessage> handleMessage(@RequestBody ChatMessage message) throws IOException {
        return chatService.handleNewMessage(message);
    }
}
