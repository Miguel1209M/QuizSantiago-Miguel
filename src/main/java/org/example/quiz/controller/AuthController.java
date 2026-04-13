package org.example.quiz.controller;

import lombok.RequiredArgsConstructor;
import org.example.quiz.dto.AuthRequest;
import org.example.quiz.dto.AuthResponse;
import org.example.quiz.dto.RegisterRequest;
import org.example.quiz.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * POST /api/auth/register
     * Body: { "user": "Santiago", "username": "santi123", "password": "1234",
     *         "role": "ADMIN", "userDriver": null }
     *
     * For DRIVER registration:
     * Body: { "user": "Miguel", "username": "miguel123", "password": "1234",
     *         "role": "DRIVER", "userDriver": "santi123" }
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    /**
     * POST /api/auth/login
     * Body: { "username": "santi123", "password": "1234" }
     * Returns: { "token": "eyJ...", "username": "santi123", "role": "ADMIN" }
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
