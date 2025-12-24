package com.smartInsure.userservice.controller;

import com.smartInsure.userservice.client.AuthClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final AuthClient authClient;

    public UserController(AuthClient authClient) {
        this.authClient = authClient;
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestHeader("Authorization") String authHeader){

        boolean valid = authClient.validateToken(authHeader);
        if(!valid){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid token");
        }
        return ResponseEntity.ok("User profile data");
    }
}
