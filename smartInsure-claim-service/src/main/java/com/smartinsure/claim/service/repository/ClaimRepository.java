package com.smartinsure.claim.service.repository;


import com.smartinsure.claim.service.entity.Claim;
import com.smartinsure.claim.service.enums.ClaimStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    List<Claim> findByUsername(String username);

    List<Claim> findByStatus(ClaimStatus status);

    Optional<Claim> findByClaimNumber(String claimNumber);

}