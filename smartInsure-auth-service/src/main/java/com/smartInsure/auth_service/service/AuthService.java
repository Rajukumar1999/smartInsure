package com.smartInsure.auth_service.service;


import com.smartInsure.auth_service.store.UserStore;
import com.smartInsure.auth_service.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String register(String username,String password){
        String encodedPassword = passwordEncoder.encode(password);
        assert encodedPassword != null;
        UserStore.USERS.put(username, encodedPassword);
        return "User "+ username + "registered with password: "+ encodedPassword;
    }
    public boolean login(String username,String password){
        String storedPassword = UserStore.USERS.get(username);
        System.out.println("user "+username);
        System.out.println("pwd "+storedPassword);

        return  storedPassword !=null && passwordEncoder.matches(password,storedPassword);
    }


}
