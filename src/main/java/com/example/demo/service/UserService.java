package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    // used by tests
    User registerCustomer(String name, String email, String rawPassword);
    User findByEmail(String email);

    // used by AuthController
    User register(User user);
    User login(String email, String password);
}
