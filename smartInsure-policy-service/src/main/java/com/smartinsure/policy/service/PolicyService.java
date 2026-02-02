package com.smartinsure.policy.service;


import com.smartinsure.policy.dto.PolicyResponse;

import java.util.List;

public interface PolicyService {

    PolicyResponse createPolicy(String username, com.smartinsure.policy.dto.CreatePolicyRequest request);

    PolicyResponse getPolicyByPolicyNumber(String policyNumber);

    List<PolicyResponse> getPoliciesForUser(String username);

    List<PolicyResponse> getPendingPolicies();

    PolicyResponse approvePolicy(String policyNumber, String adminUsername);

    PolicyResponse rejectPolicy(String policyNumber, String adminUsername);
}
