package com.smartInsure.userservice.controller;

import com.smartInsure.userservice.client.AuthClient;
import com.smartInsure.userservice.dto.UpdateProfileRequest;
import com.smartInsure.userservice.entity.UserProfile;
import com.smartInsure.userservice.service.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> me(HttpServletRequest request){
       String username = (String) request.getAttribute("username");
        System.out.println("COntroller username "+username);
        if(username==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(userProfileService.getOrCreateProfile(username));
    }
    @PutMapping("/me")
    public ResponseEntity<?> updateProfile(HttpServletRequest request, @RequestBody UpdateProfileRequest updateProfileRequest){
        String username = (String) request.getAttribute("username");
        if(username==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(userProfileService.updateProfile(username,updateProfileRequest));
    }
}
