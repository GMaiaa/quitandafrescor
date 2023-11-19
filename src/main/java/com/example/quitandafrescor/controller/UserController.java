package com.example.quitandafrescor.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quitandafrescor.dto.UserRequestDTO;
import com.example.quitandafrescor.dto.UserResponseDTO;
import com.example.quitandafrescor.dto.UserUpdateDTO;
import com.example.quitandafrescor.dto.UserUpdateDTOReturn;
import com.example.quitandafrescor.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveUser(@RequestBody @Valid UserRequestDTO data) {
        userService.saveUser(data);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<UserUpdateDTOReturn> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO data) {
        return userService.updateUser(id, data);
    }

}
