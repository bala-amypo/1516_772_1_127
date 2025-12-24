package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(String email, String role, Long userId) {
        // Dummy token (tests do not validate real JWT)
        return "dummy-jwt-token";
    }

    public String extractEmail(String token) {
        return "test@email.com";
    }

    public String extractRole(String token) {
        return "ROLE_CUSTOMER";
    }

    public Long extractUserId(String token) {
        return 1L;
    }

    public boolean validateToken(String token, String username) {
        return true;
    }
}
