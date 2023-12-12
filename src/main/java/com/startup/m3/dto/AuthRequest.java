package com.startup.m3.dto;

public record AuthRequest(
        String email,
        String password
) {
}
