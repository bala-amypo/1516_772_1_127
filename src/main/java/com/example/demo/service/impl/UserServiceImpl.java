package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User registerCustomer(String name, String email, String password) {
        if (repo.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User u = new User();
        u.setFullName(name);
        u.setEmail(email);
        u.setPassword(encoder.encode(password));
        u.setRole(User.Role.CUSTOMER);

        return repo.save(u);
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }
}
