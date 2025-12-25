package com.smartInsure.userservice.service;

import com.smartInsure.userservice.dto.UpdateProfileRequest;
import com.smartInsure.userservice.entity.UserProfile;
import com.smartInsure.userservice.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private  final UserProfileRepository userProfileRepository;


    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile getOrCreateProfile(String username){
        return userProfileRepository.findByUsername(username)
                .orElseGet(() ->{
                    UserProfile userProfile  = new UserProfile();
                    userProfile.setUsername(username);
                    return userProfileRepository.save(userProfile);
                });
    }

    public UserProfile updateProfile(String username, UpdateProfileRequest request){
        UserProfile profile = userProfileRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Profile not found"));
        profile.setFullname(request.getFullName());
        profile.setPhone(request.getPhone());
        profile.setAddress(request.getAddress());
        return userProfileRepository.save(profile);
    }
}
