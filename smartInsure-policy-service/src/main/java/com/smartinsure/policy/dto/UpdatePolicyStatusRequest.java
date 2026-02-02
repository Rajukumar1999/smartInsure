package com.smartinsure.policy.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdatePolicyStatusRequest {

    @NotBlank
    private String reason;

    public @NotBlank String getReason() {
        return reason;
    }

    public void setReason(@NotBlank String reason) {
        this.reason = reason;
    }
}
