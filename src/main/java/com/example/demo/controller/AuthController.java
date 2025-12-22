package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ================= REGISTER =================
    // POST /api/auth/register
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    // ================= LOGIN =================
    // POST /api/auth/login
    @PostMapping("/login")
    public User login(@RequestParam String email,
                      @RequestParam String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
