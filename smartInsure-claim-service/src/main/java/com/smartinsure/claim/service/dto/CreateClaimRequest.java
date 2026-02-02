package com.smartinsure.claim.service.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateClaimRequest {

    @NotBlank
    private String policyNumber;
    @NotBlank
    private String reason;

    public @NotBlank String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(@NotBlank String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public @NotBlank String getReason() {
        return reason;
    }

    public void setReason(@NotBlank String reason) {
        this.reason = reason;
    }
}
