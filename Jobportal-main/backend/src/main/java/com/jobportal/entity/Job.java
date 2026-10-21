package com.jobportal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private User recruiter;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String skillsRequired;
    private String location;
    private String experienceRequired;

    private LocalDateTime postedAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private JobStatus status = JobStatus.OPEN;

    public enum JobStatus {
        OPEN, CLOSED
    }
}
