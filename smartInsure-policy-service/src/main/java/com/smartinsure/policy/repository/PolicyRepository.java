package com.smartinsure.policy.repository;

import com.smartinsure.policy.entity.Policy;
import com.smartinsure.policy.entity.PolicyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy,Long> {

    List<Policy> findByUsername(String username);

    List<Policy> findByStatus(PolicyStatus status);

    Optional<Policy> findByPolicyNumber(String policyNumber);

    @Query("select coalesce(max(p.sequenceNo), 0) from Policy p")
    Long getMaxSequence();

}
