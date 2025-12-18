package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // REGISTER CUSTOMER
    // POST /api/auth/register
    @PostMapping("/register")
    public User register(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password) {

        return userService.registerCustomer(name, email, password);
    }

    // LOGIN (simple validation)
    // POST /api/auth/login
    @PostMapping("/login")
    public User login(@RequestParam String email,
                      @RequestParam String password) {

        User user = userService.findByEmail(email);

        if (!user.getPassword().equals(password)) {
            throw new BadRequestException("Invalid credentials");
        }

        return user;
    }
}
