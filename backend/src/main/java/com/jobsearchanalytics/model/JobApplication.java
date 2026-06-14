package com.jobsearchanalytics.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String companyName;
    private String location;

    @Enumerated(EnumType.STRING)
    private WorkType workType;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    private String salaryRange;

    @Enumerated(EnumType.STRING)
    private JobSource source;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDate dateApplied;
    private LocalDate statusDate;

    @Column(columnDefinition = "TEXT")
    private String jobUrl;

    @Column(length = 2000)
    private String notes;

    // analytics fields
    private String recruiterName;
    private String recruiterEmail;
    private Boolean referral;
    private String industry;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
