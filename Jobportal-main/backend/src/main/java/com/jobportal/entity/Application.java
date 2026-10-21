package com.jobportal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private User jobSeeker;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    private LocalDateTime appliedAt = LocalDateTime.now();

    public enum ApplicationStatus {
        APPLIED, SHORTLISTED, REJECTED
    }
}
