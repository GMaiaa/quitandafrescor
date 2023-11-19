package com.example.quitandafrescor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quitandafrescor.dto.UserRequestDTO;
import com.example.quitandafrescor.dto.UserResponseDTO;
import com.example.quitandafrescor.dto.UserUpdateDTO;
import com.example.quitandafrescor.dto.UserUpdateDTOReturn;
import com.example.quitandafrescor.model.User;
import com.example.quitandafrescor.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserRequestDTO data) {
        User userData = new User(data);
        userRepository.save(userData);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDTO::new)
                .toList();
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<UserUpdateDTOReturn> updateUser(Long id, UserUpdateDTO data) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.updateUser(data);
            userRepository.save(user);
            return ResponseEntity.ok(new UserUpdateDTOReturn(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}