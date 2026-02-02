package com.smartInsure.userservice.service;

import com.smartInsure.userservice.dto.UpdateProfileRequest;
import com.smartInsure.userservice.entity.UserProfile;
import com.smartInsure.userservice.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    public UserProfile getOrCreateProfile(String username) {
        return repository.findByUsername(username)
                .orElseGet(() -> repository.save(new UserProfile(username)));
    }

    public UserProfile updateProfile(String username, UpdateProfileRequest request) {

        UserProfile profile = getOrCreateProfile(username);

        profile.setFullName(request.getFullName());
        profile.setPhone(request.getPhone());
        profile.setAddress(request.getAddress());

        return repository.save(profile);
    }
}