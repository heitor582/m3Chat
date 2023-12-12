package com.startup.m3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;
    private String content;
    private Instant createdAt;

    public Message() {
    }

    public Message(
            final Long id,
            final User user,
            final ChatRoom chatRoom,
            final String content,
            final Instant createdAt
    ) {
        this.id = id;
        this.user = user;
        this.chatRoom = chatRoom;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static Message newMessage(
            final User user,
            final ChatRoom chatRoom,
            final String content
    ){
        return new Message(0L, user, chatRoom, content, Instant.now());
    }

    public Long getId() {
        return id;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public String getContent() {
        return content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }
}