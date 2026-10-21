package com.jobportal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "job_seeker_profile")
@Data
public class JobSeekerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String skills;
    private String experience;
    private String resumeUrl;
    private String location;
}
