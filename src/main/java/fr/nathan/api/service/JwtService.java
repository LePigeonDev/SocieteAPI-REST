package fr.nathan.api.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.nathan.api.dto.auth.AuthRequestDTO;
import fr.nathan.api.dto.auth.AuthResponseDTO;

@Service
public class JwtService {

        private final String clientId;
        private final String clientSecret;
        private final JwtEncoder jwtEncoder;

        public JwtService(
                @Value("${APP_CLIENT_ID}") String clientId,
                @Value("${APP_CLIENT_SECRET}") String clientSecret,
                JwtEncoder jwtEncoder
        ) {
                this.clientId = clientId;
                this.clientSecret = clientSecret;
                this.jwtEncoder = jwtEncoder;
        }

        public AuthResponseDTO authenticate(AuthRequestDTO request) {

        if (!clientId.equals(request.clientId())
                || !clientSecret.equals(request.clientSecret())) {

                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid client credentials"
                );
        }

        Instant now = Instant.now();
        long expiresIn = 1800;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("societe.api")
                .subject(request.clientId())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", "company.read company.write")
                .build();

        JwsHeader header = JwsHeader
                .with(MacAlgorithm.HS256)
                .type("JWT")
                .build();

        String token = jwtEncoder
                .encode(
                        JwtEncoderParameters.from(header, claims)
                )
                .getTokenValue();

        return new AuthResponseDTO(
                token,
                "Bearer",
                expiresIn
        );
        }
}