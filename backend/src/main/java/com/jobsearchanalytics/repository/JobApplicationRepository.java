package com.jobsearchanalytics.repository;

import com.jobsearchanalytics.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {
}
