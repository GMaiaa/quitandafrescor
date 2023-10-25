package com.example.quitandafrescor.dto;

import com.example.quitandafrescor.model.Roles;
import com.example.quitandafrescor.model.User;

public record UserResponseDTO(Long id, String name, String phone, String email, String password, Roles role) {

    public UserResponseDTO(User user) {

        this(
                user.getId(),
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getPassword(),
                user.getRole());
    }
}
