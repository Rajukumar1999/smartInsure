package com.smartinsure.admin.dto;


import jakarta.validation.constraints.NotBlank;

public class AdminActionRequest {

    @NotBlank
    private String action;

    public String getAction() {
        return action;
    }


}
