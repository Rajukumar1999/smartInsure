package com.smartInsure.auth_service.controller;

import ch.qos.logback.core.subst.Token;
import com.smartInsure.auth_service.dto.LoginRequest;
import com.smartInsure.auth_service.dto.RegisterRequest;
import com.smartInsure.auth_service.service.AuthService;

import com.smartInsure.auth_service.util.JwtUtil;
import jakarta.validation.Valid;
import org.apache.juli.logging.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
    @RequestMapping("/auth")
    public  class  AuthController{
    private final AuthService authService;

    private final JwtUtil jwtUtil;


    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest){
            authService.register(registerRequest.getUsername(),
                                 registerRequest.getEmail(),
                                 registerRequest.getPassword()
            );
        return ResponseEntity.ok("User Registered successfully");
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest loginRequest){
                 boolean success =
                         authService.login(
                                 loginRequest.getUsername(),
                                 loginRequest.getPassword()

        );
                if(!success){
                    return "Invalid Credentials";
                }

        return jwtUtil.generatedToken(loginRequest.getUsername());
    }

    @PostMapping("/validate")
    public Map<String, Object> validateToken(
            @RequestHeader("Authorization") String authHeader) {

        System.out.println("RAW AUTH HEADER = [" + authHeader + "]");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Authorization header");
        }

        // 🔥 MOST IMPORTANT LINE
        String token = authHeader.replace("Bearer", "").trim();

        System.out.println("JWT AFTER TRIM = [" + token + "]");

        String username = jwtUtil.extractUsername(token);

        Map<String, Object> response = new HashMap<>();
        response.put("valid", true);
        response.put("username", username);
        return response;
    }
}
