package com.jobportal.repository;

import com.jobportal.entity.Application;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByJobSeeker(User jobSeeker);
    List<Application> findByJob(Job job);
    boolean existsByJobAndJobSeeker(Job job, User jobSeeker);
}
