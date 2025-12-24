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


@RestController
    @RequestMapping("/auth")
    public  class  AuthController{
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
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

        return JwtUtil.generatedToken(loginRequest.getUsername());
    }

    @PostMapping("/validate")
    public boolean validateToken(@RequestHeader("Authorization") String authHeader){

        if(authHeader==null || !authHeader.startsWith("Bearer ")) {
            return  false;
        }
        String token = authHeader.substring(7);

        return JwtUtil.validateToken(token);
    }
}
