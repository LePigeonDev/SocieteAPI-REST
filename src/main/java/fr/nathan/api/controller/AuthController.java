package fr.nathan.api.controller;

import fr.nathan.api.dto.auth.AuthRequestDTO;
import fr.nathan.api.dto.auth.AuthResponseDTO;
import fr.nathan.api.service.JwtService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponseDTO> getToken(
            @RequestBody AuthRequestDTO request
    ) {

        return ResponseEntity.ok(
            jwtService.authenticate(request)
        );
    }
}