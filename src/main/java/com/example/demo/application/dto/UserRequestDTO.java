package com.example.demo.application.dto;

public record UserRequestDTO(
        String name,
        String email,
        String password
) {}
