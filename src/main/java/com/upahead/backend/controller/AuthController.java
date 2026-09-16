package com.upahead.backend.controller;

import com.upahead.backend.dto.UserResponse;
import com.upahead.backend.entity.User;
import com.upahead.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.upahead.backend.dto.LoginRequest;
import com.upahead.backend.dto.LoginResponse;
import com.upahead.backend.security.JwtService;

@RestController
@RequestMapping("/api/auth")
// @CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(
            UserService userService,
            JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody User user) {

        User registeredUser = userService.registerUser(user);

        UserResponse response = new UserResponse(registeredUser);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        User user = userService.loginUser(
                request.getEmail(),
                request.getPassword());

        String token = jwtService.generateToken(user.getEmailId());

        LoginResponse response = new LoginResponse(
                token,
                new UserResponse(user));

        return ResponseEntity.ok(response);
    }
}