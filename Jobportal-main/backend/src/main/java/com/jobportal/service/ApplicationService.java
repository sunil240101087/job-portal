package com.jobportal.service;

import com.jobportal.entity.Application;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    public Application apply(Long jobId, String seekerEmail) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        User seeker = userRepository.findByEmail(seekerEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (applicationRepository.existsByJobAndJobSeeker(job, seeker)) {
            throw new RuntimeException("You already applied to this job");
        }

        Application application = new Application();
        application.setJob(job);
        application.setJobSeeker(seeker);

        return applicationRepository.save(application);
    }

    public List<Application> myApplications(String seekerEmail) {
        User seeker = userRepository.findByEmail(seekerEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return applicationRepository.findByJobSeeker(seeker);
    }

    public List<Application> applicantsForJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return applicationRepository.findByJob(job);
    }

    public Application updateStatus(Long applicationId, Application.ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        return applicationRepository.save(application);
    }
}
