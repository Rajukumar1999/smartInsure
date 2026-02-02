package com.smartinsure.admin.controller;

import com.smartinsure.admin.dto.AdminActionRequest;
import com.smartinsure.admin.entity.AdminAction;
import com.smartinsure.admin.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/action")
    public ResponseEntity<?> logAction(
            @RequestHeader("X-Username") String username,
            @RequestHeader("X-Role") String role,
            @RequestBody @Valid AdminActionRequest request) {

        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        AdminAction saved = adminService.logAction(username, request);
        return ResponseEntity.ok(saved);
    }
}