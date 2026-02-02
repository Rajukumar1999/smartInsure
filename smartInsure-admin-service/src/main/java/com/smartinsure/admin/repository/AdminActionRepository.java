package com.smartinsure.admin.repository;


import com.smartinsure.admin.entity.AdminAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminActionRepository extends JpaRepository<AdminAction, Long> {
}