package com.jobportal.dto;

import lombok.Data;

@Data
public class JobDto {
    private Long id;
    private String title;
    private String description;
    private String skillsRequired;
    private String location;
    private String experienceRequired;
    private String status;
    private String recruiterCompany;
}
