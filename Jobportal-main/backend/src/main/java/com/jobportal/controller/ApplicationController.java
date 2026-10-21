package com.jobportal.controller;

import com.jobportal.entity.Application;
import com.jobportal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/{jobId}")
    public ResponseEntity<Application> apply(@PathVariable Long jobId, Authentication authentication) {
        return ResponseEntity.ok(applicationService.apply(jobId, authentication.getName()));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Application>> myApplications(Authentication authentication) {
        return ResponseEntity.ok(applicationService.myApplications(authentication.getName()));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>> applicantsForJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(applicationService.applicantsForJob(jobId));
    }

    @PutMapping("/{applicationId}/status")
    public ResponseEntity<Application> updateStatus(@PathVariable Long applicationId,
                                                      @RequestBody Map<String, String> body) {
        Application.ApplicationStatus status = Application.ApplicationStatus.valueOf(body.get("status"));
        return ResponseEntity.ok(applicationService.updateStatus(applicationId, status));
    }
}
