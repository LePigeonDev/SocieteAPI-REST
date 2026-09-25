package fr.nathan.api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class is used to handle health check requests.
@RestController
@RequestMapping("/api")
public class HealthController {

    // This method is used to check the health of the application.
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }
}
