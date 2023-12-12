package com.startup.m3.dto;

import com.startup.m3.entity.ChatRoom;

public record ChatRoomListDto(
        Long id,
        String name
) {
    public static ChatRoomListDto from(final ChatRoom chatRoom) {
        return new ChatRoomListDto(chatRoom.getId(), chatRoom.getName());
    }
}
