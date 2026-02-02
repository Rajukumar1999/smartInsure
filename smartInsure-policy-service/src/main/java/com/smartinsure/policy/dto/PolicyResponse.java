package com.smartinsure.policy.dto;

import com.smartinsure.policy.entity.Policy;
import com.smartinsure.policy.entity.PolicyStatus;

import java.time.LocalDate;

public class PolicyResponse {

    private String policyNumber;
    private String policyType;
    private PolicyStatus status;
    private LocalDate startDate;
    private LocalDate endDate;

    public static PolicyResponse fromEntity(Policy policy) {
        PolicyResponse res = new PolicyResponse();
        res.policyNumber = policy.getPolicyNumber();
        res.policyType = policy.getPolicyType();
        res.status = policy.getStatus();
        res.startDate = policy.getStartDate();
        res.endDate = policy.getEndDate();
        return res;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getPolicyType() {
        return policyType;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}

