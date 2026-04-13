package org.example.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String user;
    private String username;
    private String password;
    private String role;       // "ADMIN" or "DRIVER"
    private String userDriver; // Only needed when registering a DRIVER (admin's username)
}
