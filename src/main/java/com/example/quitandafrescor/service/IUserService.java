package com.example.quitandafrescor.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.quitandafrescor.dto.UserRequestDTO;
import com.example.quitandafrescor.dto.UserResponseDTO;
import com.example.quitandafrescor.dto.UserUpdateDTO;
import com.example.quitandafrescor.dto.UserUpdateDTOReturn;

public interface IUserService {
    void saveUser(UserRequestDTO data);

    List<UserResponseDTO> getAllUsers();

    ResponseEntity<Void> deleteUser(Long id);

    ResponseEntity<UserUpdateDTOReturn> updateUser(Long id, UserUpdateDTO data);
}
