package com.jobportal.service;

import com.jobportal.dto.JobDto;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    public Job postJob(String recruiterEmail, JobDto dto) {
        User recruiter = userRepository.findByEmail(recruiterEmail)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        Job job = new Job();
        job.setRecruiter(recruiter);
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setSkillsRequired(dto.getSkillsRequired());
        job.setLocation(dto.getLocation());
        job.setExperienceRequired(dto.getExperienceRequired());

        return jobRepository.save(job);
    }

    public Job updateJob(Long jobId, JobDto dto) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setSkillsRequired(dto.getSkillsRequired());
        job.setLocation(dto.getLocation());
        job.setExperienceRequired(dto.getExperienceRequired());

        return jobRepository.save(job);
    }

    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }

    public List<Job> searchJobs(String search, String location, String skills) {
        return jobRepository.searchJobs(search, location, skills);
    }

    public List<Job> myJobs(String recruiterEmail) {
        User recruiter = userRepository.findByEmail(recruiterEmail)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));
        return jobRepository.findByRecruiter(recruiter);
    }

    public Job getJob(Long jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }
}
