package fr.nathan.api.dto.auth;

// This class is used to represent the request for authentication.
public record AuthRequestDTO(
    String clientId,
    String clientSecret
) {}
