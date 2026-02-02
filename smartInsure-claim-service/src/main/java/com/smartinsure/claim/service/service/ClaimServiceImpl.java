package com.smartinsure.claim.service.service;


import com.smartinsure.claim.service.dto.ClaimResponse;
import com.smartinsure.claim.service.dto.CreateClaimRequest;
import com.smartinsure.claim.service.entity.Claim;
import com.smartinsure.claim.service.enums.ClaimStatus;
import com.smartinsure.claim.service.repository.ClaimRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository repository;

    public ClaimServiceImpl(ClaimRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClaimResponse createClaim(String username, CreateClaimRequest request) {
        String claimNumber = "CLM-" + UUID.randomUUID().toString().substring(0, 8);
        Claim claim = new Claim(
                claimNumber,
                username,
                request.getPolicyNumber(),
                request.getReason()
        );
        return ClaimResponse.fromEntity(repository.save(claim));
    }

    @Override
    public List<ClaimResponse> getMyClaims(String username) {
        return repository.findByUsername(username)
                .stream()
                .map(ClaimResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClaimResponse> getPendingClaims() {
        return repository.findByStatus(ClaimStatus.PENDING)
                .stream()
                .map(ClaimResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ClaimResponse approveClaim(String claimNumber) {
        Claim claim = repository.findByClaimNumber(claimNumber)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
        claim.setStatus(ClaimStatus.APPROVED);
        return ClaimResponse.fromEntity(claim);
    }

    @Override
    public ClaimResponse rejectClaim(String claimNumber) {
        Claim claim = repository.findByClaimNumber(claimNumber)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
        claim.setStatus(ClaimStatus.REJECTED);
        return ClaimResponse.fromEntity(claim);
    }
}