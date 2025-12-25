package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public User registerCustomer(String name, String email, String rawPassword) {
        if (repo.findByEmail(email).isPresent()) {
            throw new RuntimeException("email already exists");
        }

        User u = new User();
        u.setFullName(name);
        u.setEmail(email);
        u.setPassword(encoder.encode(rawPassword));
        u.setRole(User.Role.CUSTOMER);
        return repo.save(u);
    }

    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }
}
