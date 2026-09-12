package com.upahead.backend.service;

import com.upahead.backend.entity.User;
import com.upahead.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    public User loginUser(String emailId, String password) {

        User user = userRepository.findByEmailId(emailId)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        System.out.println("=================================");
        System.out.println("Email from request: " + emailId);
        System.out.println("Password from request: " + password);
        System.out.println("Password from DB: " + user.getPassword());

        boolean matches = passwordEncoder.matches(
                password,
                user.getPassword());

        System.out.println("Password matches: " + matches);
        System.out.println("=================================");

        if (!matches) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;

    }
}