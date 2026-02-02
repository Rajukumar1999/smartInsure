package com.smartinsure.policy.dto;

import jakarta.validation.constraints.NotBlank;

public class CreatePolicyRequest {
    @NotBlank
    private String policyType;

    public @NotBlank String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(@NotBlank String policyType) {
        this.policyType = policyType;
    }
}
