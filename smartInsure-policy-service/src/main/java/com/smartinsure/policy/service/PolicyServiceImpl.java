package com.smartinsure.policy.service;


import com.smartinsure.policy.dto.PolicyResponse;
import com.smartinsure.policy.entity.Policy;
import com.smartinsure.policy.entity.PolicyStatus;
import com.smartinsure.policy.repository.PolicyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;

    public PolicyServiceImpl(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public PolicyResponse createPolicy(String username, com.smartinsure.policy.dto.CreatePolicyRequest request) {
        Long nextSeq = policyRepository.getMaxSequence() + 1;

        String year = String.valueOf(LocalDate.now().getYear());

        String policyNumber = String.format(
                "POL-%s-%06d",
                year,
                nextSeq
        );

        Policy policy = new Policy(
                policyNumber,
                username,
                request.getPolicyType()
        );
        policy.setSequenceNo(nextSeq);

        return PolicyResponse.fromEntity(policyRepository.save(policy));
    }

    @Override
    public PolicyResponse getPolicyByPolicyNumber(String policyNumber) {
        Policy policy = policyRepository.findByPolicyNumber(policyNumber)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
        return PolicyResponse.fromEntity(policy);
    }

    @Override
    public List<PolicyResponse> getPoliciesForUser(String username) {
        return policyRepository.findByUsername(username)
                .stream()
                .map(PolicyResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PolicyResponse> getPendingPolicies() {
        return policyRepository.findByStatus(PolicyStatus.PENDING)
                .stream()
                .map(PolicyResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PolicyResponse approvePolicy(String policyNumber, String adminUsername) {
        Policy policy = policyRepository.findByPolicyNumber(policyNumber)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
        policy.setStatus(PolicyStatus.APPROVED);
        return PolicyResponse.fromEntity(policy);
    }

    @Override
    public PolicyResponse rejectPolicy(String policyNumber, String adminUsername) {
        Policy policy = policyRepository.findByPolicyNumber(policyNumber)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
        policy.setStatus(PolicyStatus.REJECTED);
        return PolicyResponse.fromEntity(policy);
    }
}
