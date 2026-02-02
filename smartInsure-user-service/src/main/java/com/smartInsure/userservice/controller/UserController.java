package com.smartInsure.userservice.controller;

import com.smartInsure.userservice.dto.UpdateProfileRequest;
import com.smartInsure.userservice.entity.UserProfile;
import com.smartInsure.userservice.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserProfileService userProfileService;

    public UserController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/me")
    public UserProfile me(@RequestHeader("X-Username") String username) {
        return userProfileService.getOrCreateProfile(username);
    }
    @PutMapping("/me")
    public UserProfile update(
            @RequestHeader("X-Username") String username,
            @RequestBody UpdateProfileRequest request
    ) {
        return userProfileService.updateProfile(username, request);
    }
}