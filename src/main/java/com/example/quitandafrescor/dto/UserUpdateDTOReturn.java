package com.example.quitandafrescor.dto;

import com.example.quitandafrescor.model.User;

public record UserUpdateDTOReturn(Long id, String name, String phone, String email, String password) {

    public UserUpdateDTOReturn(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getPassword());
    }
}
