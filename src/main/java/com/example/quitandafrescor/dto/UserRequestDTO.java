package com.example.quitandafrescor.dto;

import jakarta.validation.constraints.Email;

public record UserRequestDTO(
        String name,
        String phone,
        @Email String email,
        String password) {

}
