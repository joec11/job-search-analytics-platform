package com.jobsearchanalytics.dto.request;

import com.jobsearchanalytics.model.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateJobApplicationRequest(

        @NotBlank String jobTitle,
        @NotBlank String companyName,
        String location,

        WorkType workType,
        EmploymentType employmentType,

        String salaryRange,

        JobSource source,
        ApplicationStatus status,

        LocalDate dateApplied,
        LocalDate statusDate,

        String jobUrl,

        @Size(max = 2000)
        String notes,

        // analytics
        String recruiterName,
        String recruiterEmail,
        Boolean referral,
        String industry
) {}
