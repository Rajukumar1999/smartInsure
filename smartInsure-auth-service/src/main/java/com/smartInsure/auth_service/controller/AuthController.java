package com.smartInsure.auth_service.controller;

import com.smartInsure.auth_service.dto.AuthValidateResponse;
import com.smartInsure.auth_service.dto.LoginRequest;
import com.smartInsure.auth_service.dto.RegisterRequest;
import com.smartInsure.auth_service.entity.User;
import com.smartInsure.auth_service.service.AuthService;
import com.smartInsure.auth_service.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest request) {
        authService.register(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest request) {

        User user = authService.authenticate(
                request.getUsername(),
                request.getPassword()
        );

        return jwtUtil.generateToken(
                user.getUsername(),
                user.getRole()
        );
    }

    @PostMapping("/validate")
    public AuthValidateResponse validate(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);

        String username = jwtUtil.extractUsername(token);
        String role = jwtUtil.extractRole(token);

        return new AuthValidateResponse(true, username, role);
    }
}
