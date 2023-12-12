package com.startup.m3.services;

import com.startup.m3.entity.ChatRoom;
import com.startup.m3.entity.Message;
import com.startup.m3.entity.User;
import com.startup.m3.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatGPTService chatGPTService;

    public MessageService(MessageRepository messageRepository, ChatGPTService chatGPTService) {
        this.messageRepository = messageRepository;
        this.chatGPTService = chatGPTService;
    }

    public List<Message> getMessagesByChatRoom(ChatRoom chatRoom) {
        return messageRepository.findByChatRoomOrderByCreatedAtAsc(chatRoom);
    }

    public Message createMessage(User user, ChatRoom chatRoom, String content) {
        Message userMessage = Message.newMessage(user, chatRoom, content);
        messageRepository.save(userMessage);

        return userMessage;
    }
}
