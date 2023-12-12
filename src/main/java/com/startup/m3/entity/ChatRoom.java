package com.startup.m3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "chat_room")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinTable(
            name = "user_chatroom",
            joinColumns = @JoinColumn(name = "chatroom_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private User user;
    private Instant created_at;

    public ChatRoom() {
    }

    public ChatRoom(
            final Long id,
            final String name,
            final User user,
            final Instant created_at
    ) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.created_at = created_at;
    }

    public static ChatRoom newChatRoom(
            final String name,
            final User user
    ){
        return new ChatRoom(0L, name, user, Instant.now());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public Instant getCreated_at() {
        return created_at;
    }
}