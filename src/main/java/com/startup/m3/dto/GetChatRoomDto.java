package com.startup.m3.dto;

import com.startup.m3.entity.ChatRoom;
import com.startup.m3.entity.Message;

import java.util.List;

public record GetChatRoomDto(
        Long id,
        String name,
        List<MessageDTO> messages
) {
    public static GetChatRoomDto from(ChatRoom chatRoom, List<Message> messages) {
        return new GetChatRoomDto(chatRoom.getId(), chatRoom.getName(), messages.stream().map(MessageDTO::from).toList());
    }

    public static GetChatRoomDto from(ChatRoom chatRoom) {
        return new GetChatRoomDto(chatRoom.getId(), chatRoom.getName(), List.of());
    }

    public record MessageDTO(
            Long id,
            String content,
            String createdAt,
            String companyName
    ){
        public static MessageDTO from(Message message) {
            return new MessageDTO(message.getId(), message.getContent(), message.getCreatedAt().toString(), message.getUser().getCompanyName());
        }
    }
}
