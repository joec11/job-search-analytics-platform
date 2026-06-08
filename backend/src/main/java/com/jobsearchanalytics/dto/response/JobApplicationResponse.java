package com.jobsearchanalytics.dto.response;

import com.jobsearchanalytics.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record JobApplicationResponse(

        Long id,
        String jobTitle,
        String companyName,
        String location,

        WorkType workType,
        EmploymentType employmentType,

        String salaryRange,
        JobSource source,
        ApplicationStatus status,

        LocalDate dateApplied,
        LocalDate statusDate,

        String jobUrl,
        String notes,

        String recruiterName,
        String recruiterEmail,
        Boolean referral,
        String industry,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
