package com.smartinsure.policy.controller;


import com.smartinsure.policy.dto.PolicyResponse;
import com.smartinsure.policy.service.PolicyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }


    // Create new policy (USER)
    @PostMapping
    public ResponseEntity<PolicyResponse> createPolicy(
            @RequestHeader("X-Username") String username,
            @RequestBody @Valid com.smartinsure.policy.dto.CreatePolicyRequest request
    ) {
        return ResponseEntity.ok(
                policyService.createPolicy(username, request)
        );
    }

    //  Get my policies (USER)
    @GetMapping("/me")
    public ResponseEntity<List<PolicyResponse>> myPolicies(
            @RequestHeader("X-Username") String username
    ) {
        return ResponseEntity.ok(
                policyService.getPoliciesForUser(username)
        );
    }

    // Get single policy by policy number (USER / ADMIN)
    @GetMapping("/{policyNumber}")
    public ResponseEntity<PolicyResponse> getPolicy(
            @PathVariable String policyNumber
    ) {
        return ResponseEntity.ok(
                policyService.getPolicyByPolicyNumber(policyNumber)
        );
    }


    // Get all pending policies (ADMIN)
    @GetMapping("/admin/pending")
    public ResponseEntity<List<PolicyResponse>> pendingPolicies() {
        return ResponseEntity.ok(
                policyService.getPendingPolicies()
        );
    }

    // Approve policy (ADMIN)
    @PutMapping("/admin/{policyNumber}/approve")
    public ResponseEntity<PolicyResponse> approvePolicy(
            @PathVariable String policyNumber,
            @RequestHeader("X-Username") String adminUsername
    ) {
        return ResponseEntity.ok(
                policyService.approvePolicy(policyNumber, adminUsername)
        );
    }

    //  Reject policy (ADMIN)
    @PutMapping("/admin/{policyNumber}/reject")
    public ResponseEntity<PolicyResponse> rejectPolicy(
            @PathVariable String policyNumber,
            @RequestHeader("X-Username") String adminUsername
    ) {
        return ResponseEntity.ok(
                policyService.rejectPolicy(policyNumber, adminUsername)
        );
    }
}

