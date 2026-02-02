package com.smartinsure.claim.service.dto;



import com.smartinsure.claim.service.entity.Claim;
import com.smartinsure.claim.service.enums.ClaimStatus;

import java.time.LocalDateTime;

public class ClaimResponse {

    private String claimNumber;
    private String policyNumber;
    private String reason;
    private ClaimStatus status;
    private LocalDateTime createdAt;

    public static ClaimResponse fromEntity(Claim claim) {
        ClaimResponse res = new ClaimResponse();
        res.claimNumber = claim.getClaimNumber();
        res.policyNumber = claim.getPolicyNumber();
        res.reason = claim.getReason();
        res.status = claim.getStatus();
        res.createdAt = claim.getCreatedAt();
        return res;
    }

    public String getClaimNumber() { return claimNumber; }
    public String getPolicyNumber() { return policyNumber; }
    public String getReason() { return reason; }
    public ClaimStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}