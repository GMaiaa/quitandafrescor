package com.example.quitandafrescor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quitandafrescor.dto.UserRequestDTO;
import com.example.quitandafrescor.dto.UserResponseDTO;
import com.example.quitandafrescor.model.Product;
import com.example.quitandafrescor.model.User;
import com.example.quitandafrescor.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public void saveUser(@RequestBody @Valid UserRequestDTO data) {
        User userData = new User(data);

        userRepository.save(userData);
        return;
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> userList = userRepository.findAll().stream().map(UserResponseDTO::new)
                .toList();
        return userList;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        userRepository.delete(optionalUser.get());
    }
}
