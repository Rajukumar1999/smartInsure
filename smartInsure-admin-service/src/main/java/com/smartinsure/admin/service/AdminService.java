package com.smartinsure.admin.service;

import com.smartinsure.admin.dto.AdminActionRequest;
import com.smartinsure.admin.entity.AdminAction;
import com.smartinsure.admin.repository.AdminActionRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminActionRepository repository;

    public AdminService(AdminActionRepository repository) {
        this.repository = repository;
    }

    public AdminAction logAction(String adminUsername, AdminActionRequest request) {

        AdminAction action = new AdminAction(
                adminUsername,
                request.getAction()
        );

        return repository.save(action);
    }
}