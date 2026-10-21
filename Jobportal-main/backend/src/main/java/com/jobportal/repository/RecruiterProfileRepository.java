package com.jobportal.repository;

import com.jobportal.entity.RecruiterProfile;
import com.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile, Long> {
    Optional<RecruiterProfile> findByUser(User user);
}
