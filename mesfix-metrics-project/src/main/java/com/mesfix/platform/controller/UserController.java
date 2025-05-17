package com.mesfix.platform.controller;

import com.mesfix.platform.domain.dto.UserDto;
import com.mesfix.platform.domain.translate.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mesfix.platform.domain.User;
import com.mesfix.platform.repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userRepository.save(userMapper.toEntity(user));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userMapper.toDto(existingUser), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestBody UserDto user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.delete(existingUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
