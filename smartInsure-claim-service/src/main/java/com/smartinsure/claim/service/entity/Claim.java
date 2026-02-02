package com.smartinsure.claim.service.entity;


import com.smartinsure.claim.service.enums.ClaimStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String claimNumber;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String policyNumber;

    @Column(nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus status;

    private LocalDateTime createdAt;

    protected Claim() {}

    public Claim(String claimNumber, String username, String policyNumber, String reason) {
        this.claimNumber = claimNumber;
        this.username = username;
        this.policyNumber = policyNumber;
        this.reason = reason;
        this.status = ClaimStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getClaimNumber() { return claimNumber; }
    public String getUsername() { return username; }
    public String getPolicyNumber() { return policyNumber; }
    public String getReason() { return reason; }
    public ClaimStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setStatus(ClaimStatus status) { this.status = status; }
}