package fr.nathan.api.dto.auth;

public record AuthRequestDTO(
    String clientId,
    String clientSecret
) {}
