package com.smartInsure.auth_service.dto;

public class AuthValidateResponse {
    private boolean valid;
    private String username;
    private String role;

    public AuthValidateResponse(boolean valid, String username, String role) {
        this.valid = valid;
        this.username = username;
        this.role = role;
    }

    public boolean isValid() {
        return valid;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
