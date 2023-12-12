package com.startup.m3.services;

import com.startup.m3.dto.ChatMessage;
import com.startup.m3.entity.ChatRoom;
import com.startup.m3.entity.Message;
import com.startup.m3.entity.User;
import com.startup.m3.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ChatService {

    private final MessageService messageService;
    private final ChatGPTService chatGPTService;
    private final UserInfoService userService;
    private final ChatRoomRepository repository;
    @Value("${internal.email}")
    private String internalEmail;

    public ChatService(
           final MessageService messageService,
           final ChatGPTService chatGPTService,
           final UserInfoService userService,
           final ChatRoomRepository repository
    ) {
        this.messageService = messageService;
        this.chatGPTService = chatGPTService;
        this.userService = userService;
        this.repository = repository;
    }

    public List<ChatMessage> handleNewMessage(final ChatMessage chatMessage) {
        ChatRoom chatRoom = repository.findById(chatMessage.chatRoomId()).get();
        User user = chatRoom.getUser();

        Message userMessageContent = messageService.createMessage(user, chatRoom, chatMessage.content());
        String chatGPTResponse = chatGPTService.sendMessage(userMessageContent.getContent());

        User chatGpt = userService.findBy(internalEmail);
        messageService.createMessage(chatGpt, chatRoom, chatGPTResponse);

        ChatMessage userWebSocketMessage = new ChatMessage(user.getCompanyName(), chatRoom.getId(), userMessageContent.getContent());
        ChatMessage chatGPTWebSocketMessage = new ChatMessage(chatGpt.getCompanyName(), chatRoom.getId(), chatGPTResponse);

        return List.of(userWebSocketMessage, chatGPTWebSocketMessage);
    }
}
