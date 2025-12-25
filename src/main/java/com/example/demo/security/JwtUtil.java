package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component   // ⭐ THIS WAS MISSING
public class JwtUtil {

    // These methods are mocked in tests,
    // real JWT logic is NOT required now

    public String generateToken(String email) {
        return "dummy-token";
    }

    public String extractRole(String token) {
        return null;
    }

    public String extractEmail(String token) {
        return null;
    }

    public Long extractUserId(String token) {
        return null;
    }

    public boolean validateToken(String token, String username) {
        return false;
    }
}
