package com.jobsearchanalytics.dto.request;

import com.jobsearchanalytics.model.*;

import java.time.LocalDate;

public record UpdateJobApplicationRequest(

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
        String industry
) {}
