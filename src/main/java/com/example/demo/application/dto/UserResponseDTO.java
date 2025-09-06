package com.example.demo.application.dto;

import java.util.UUID;
import com.example.demo.domain.model.User;

public record UserResponseDTO(
        UUID id,
        String name,
        String email
) {
    public static UserResponseDTO from(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }
}
