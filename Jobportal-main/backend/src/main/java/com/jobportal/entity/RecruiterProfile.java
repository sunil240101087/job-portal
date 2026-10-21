package com.jobportal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "recruiter_profile")
@Data
public class RecruiterProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String companyName;
    private String companyWebsite;
}
