package com.startup.m3.dto;

import com.startup.m3.entity.User;

public record UserDto(
        Long id,
        String email
) {
    public static UserDto from(final User user){
        return  new UserDto(user.getId(), user.getEmail());
    }
}
