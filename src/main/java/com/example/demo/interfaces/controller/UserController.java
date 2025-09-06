package com.example.demo.interfaces.controller;

import com.example.demo.application.dto.UserRequestDTO;
import com.example.demo.application.dto.UserResponseDTO;
import com.example.demo.application.handler.CreateUserHandler;
import com.example.demo.domain.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserHandler createUserHandler;
    private final UserRepository userRepository;

    public UserController(CreateUserHandler createUserHandler, UserRepository userRepository) {
        this.createUserHandler = createUserHandler;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(createUserHandler.handle(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAll() {
        var users = userRepository.findAll()
                .stream()
                .map(UserResponseDTO::from)
                .toList();

        return ResponseEntity.ok(users);
    }
}
