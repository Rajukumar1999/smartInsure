package com.smartinsure.claim.service.service;


import com.smartinsure.claim.service.dto.ClaimResponse;
import com.smartinsure.claim.service.dto.CreateClaimRequest;

import java.util.List;

public interface ClaimService {

    ClaimResponse createClaim(String username, CreateClaimRequest request);

    List<ClaimResponse> getMyClaims(String username);

    List<ClaimResponse> getPendingClaims();

    ClaimResponse approveClaim(String claimNumber);

    ClaimResponse rejectClaim(String claimNumber);
}