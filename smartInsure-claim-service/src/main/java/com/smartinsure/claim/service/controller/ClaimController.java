package com.smartinsure.claim.service.controller;

import com.smartinsure.claim.service.dto.ClaimResponse;
import com.smartinsure.claim.service.dto.CreateClaimRequest;
import com.smartinsure.claim.service.service.ClaimService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimService service;

    public ClaimController(ClaimService service) {
        this.service = service;
    }

    // USER: create claim
    @PostMapping
    public ResponseEntity<ClaimResponse> create(
            @RequestHeader("X-Username") String username,
            @RequestHeader("X-Role") String role,
            @RequestBody @Valid CreateClaimRequest request
    ) {
        if (!"USER".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(service.createClaim(username, request));
    }

    // USER: my claims
    @GetMapping("/me")
    public ResponseEntity<List<ClaimResponse>> myClaims(
            @RequestHeader("X-Username") String username
    ) {
        return ResponseEntity.ok(service.getMyClaims(username));
    }

    // ADMIN: pending claims
    @GetMapping("/admin/pending")
    public ResponseEntity<List<ClaimResponse>> pending(
            @RequestHeader("X-Role") String role
    ) {
        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(service.getPendingClaims());
    }

    // ADMIN: approve
    @PutMapping("/admin/{claimNumber}/approve")
    public ResponseEntity<ClaimResponse> approve(
            @PathVariable String claimNumber,
            @RequestHeader("X-Role") String role
    ) {
        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(service.approveClaim(claimNumber));
    }

    // ADMIN: reject
    @PutMapping("/admin/{claimNumber}/reject")
    public ResponseEntity<ClaimResponse> reject(
            @PathVariable String claimNumber,
            @RequestHeader("X-Role") String role
    ) {
        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(service.rejectClaim(claimNumber));
    }
}
