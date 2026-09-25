package fr.nathan.api.controller;

import fr.nathan.api.dto.auth.AuthRequestDTO;
import fr.nathan.api.dto.auth.AuthResponseDTO;
import fr.nathan.api.service.JwtService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// This class is used to handle authentication requests.
@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    // This method is used to authenticate a user and return a JWT token.
    @PostMapping("/token")
    public ResponseEntity<AuthResponseDTO> getToken(
            @RequestBody AuthRequestDTO request
    ) {

        return ResponseEntity.ok(
            jwtService.authenticate(request)
        );
    }
}