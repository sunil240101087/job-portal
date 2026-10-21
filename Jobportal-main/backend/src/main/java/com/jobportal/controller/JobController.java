package com.jobportal.controller;

import com.jobportal.dto.JobDto;
import com.jobportal.entity.Job;
import com.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> searchJobs(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String skills) {
        return ResponseEntity.ok(jobService.searchJobs(search, location, skills));
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(jobService.getJob(jobId));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Job>> myJobs(Authentication authentication) {
        return ResponseEntity.ok(jobService.myJobs(authentication.getName()));
    }

    @PostMapping
    public ResponseEntity<Job> postJob(@RequestBody JobDto dto, Authentication authentication) {
        return ResponseEntity.ok(jobService.postJob(authentication.getName(), dto));
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable Long jobId, @RequestBody JobDto dto) {
        return ResponseEntity.ok(jobService.updateJob(jobId, dto));
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.noContent().build();
    }
}
