package com.startup.m3.dto;

import com.startup.m3.entity.User;

public record CreateUserDto(
        Long id,
        String email,
        String companyName
) {
    public static CreateUserDto from(final User user){
        return new CreateUserDto(user.getId(), user.getEmail(), user.getCompanyName());
    }
}
