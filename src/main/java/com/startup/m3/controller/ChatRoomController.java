package com.startup.m3.controller;

import com.startup.m3.dto.ChatRoomListDto;
import com.startup.m3.dto.CreateChatRoomDto;
import com.startup.m3.dto.GetChatRoomDto;
import com.startup.m3.entity.ChatRoom;
import com.startup.m3.services.ChatRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chatrooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ChatRoomController(final ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @GetMapping("/{chatRoomId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<GetChatRoomDto> getChatRoom(@PathVariable Long chatRoomId) {
        return ResponseEntity.ok(chatRoomService.getChatRoomById(chatRoomId));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<ChatRoomListDto>> getAllChatRooms() {
        List<ChatRoomListDto> chatRooms = chatRoomService.getAllChatRooms();
        return ResponseEntity.ok(chatRooms);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<GetChatRoomDto> createChatRoom(Authentication authentication, @RequestBody CreateChatRoomDto dto) {
        ChatRoom newChatRoom = chatRoomService.createChatRoom(authentication.getName(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetChatRoomDto.from(newChatRoom));
    }

    @DeleteMapping("/{chatRoomId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable Long chatRoomId) {
        chatRoomService.deleteChatRoom(chatRoomId);
        return ResponseEntity.noContent().build();
    }
}
