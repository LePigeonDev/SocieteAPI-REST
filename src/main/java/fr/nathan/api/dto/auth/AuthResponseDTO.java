package fr.nathan.api.dto.auth;

// This class is used to represent the response of an authentication request.
public record AuthResponseDTO(
    String token,
    String type,
    long expiresIn
) {}
