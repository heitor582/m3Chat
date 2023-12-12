package com.startup.m3.services;

import com.startup.m3.dto.ChatRoomListDto;
import com.startup.m3.dto.CreateChatRoomDto;
import com.startup.m3.dto.GetChatRoomDto;
import com.startup.m3.entity.ChatRoom;
import com.startup.m3.entity.User;
import com.startup.m3.repository.ChatRoomRepository;
import com.startup.m3.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final MessageService messageService;

    public ChatRoomService(final ChatRoomRepository chatRoomRepository, final UserRepository userRepository, final MessageService messageService) {
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    @Transactional
    public GetChatRoomDto getChatRoomById(final Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow();
        return GetChatRoomDto.from(chatRoom,  messageService.getMessagesByChatRoom(chatRoom));
    }

    public List<ChatRoomListDto> getAllChatRooms() {
        return chatRoomRepository.findAll().stream().map(ChatRoomListDto::from).toList();
    }

    public ChatRoom createChatRoom(String userEmail, CreateChatRoomDto dto) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        return chatRoomRepository.save(ChatRoom.newChatRoom(dto.name(), user));
    }

    public void deleteChatRoom(Long chatRoomId) {
        chatRoomRepository.deleteById(chatRoomId);
    }
}
