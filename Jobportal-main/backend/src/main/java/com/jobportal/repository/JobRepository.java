package com.jobportal.repository;

import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByRecruiter(User recruiter);

    @Query("SELECT j FROM Job j WHERE " +
           "(:search IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
           "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
           "(:skills IS NULL OR LOWER(j.skillsRequired) LIKE LOWER(CONCAT('%', :skills, '%')))")
    List<Job> searchJobs(@Param("search") String search,
                          @Param("location") String location,
                          @Param("skills") String skills);
}
