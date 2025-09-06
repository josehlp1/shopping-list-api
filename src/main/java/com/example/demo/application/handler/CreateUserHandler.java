package com.example.demo.application.handler;

import com.example.demo.application.dto.UserRequestDTO;
import com.example.demo.application.dto.UserResponseDTO;
import com.example.demo.domain.exception.InvalidInputDataException;
import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.infrastructure.repository.UserJpaRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserHandler {

    private final UserJpaRepository userJpaRepository;

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserHandler(UserRepository repository, PasswordEncoder passwordEncoder, UserJpaRepository userJpaRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.userJpaRepository = userJpaRepository;
    }

    public UserResponseDTO handle(UserRequestDTO dto) {
        userJpaRepository.findByEmail(dto.email())
                .ifPresent(u -> {
                    throw new InvalidInputDataException("E-mail inválido: " + dto.email());
                });
        
        if (dto.password().length() < 8) {
            throw new InvalidInputDataException("Senha muito curta");
        }

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));

        User saved = repository.save(user);

        return UserResponseDTO.from(saved);
    }
}
