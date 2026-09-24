package fr.nathan.api.dto.auth;

public record AuthResponseDTO(
    String token,
    String type,
    long expiresIn
) {}
