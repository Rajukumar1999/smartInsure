package com.smartInsure.auth_service.controller;

import ch.qos.logback.core.subst.Token;
import com.smartInsure.auth_service.dto.LoginRequest;
import com.smartInsure.auth_service.dto.RegisterRequest;
import com.smartInsure.auth_service.service.AuthService;
import com.smartInsure.auth_service.util.JwtUtil;
import jakarta.validation.Valid;
import org.apache.juli.logging.Log;
import org.springframework.web.bind.annotation.*;


@RestController
    @RequestMapping("/auth")
    public  class  AuthController{
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
        public String register(@RequestBody @Valid RegisterRequest registerRequest){
            return authService.register(registerRequest.getUsername(),
                                        registerRequest.getPassword());
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
        System.out.println("HEADER "+authHeader);
        if(authHeader==null || !authHeader.startsWith("Bearer ")) {
            return  false;
        }
        String token = authHeader.substring(7);
        System.out.println("TOKEN"+ token);
        return JwtUtil.validateToken(token);
    }
}
