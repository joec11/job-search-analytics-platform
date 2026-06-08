package com.jobsearchanalytics.service;

import com.jobsearchanalytics.dto.request.CreateJobApplicationRequest;
import com.jobsearchanalytics.dto.request.UpdateJobApplicationRequest;
import com.jobsearchanalytics.dto.response.JobApplicationResponse;
import com.jobsearchanalytics.exception.ResourceNotFoundException;
import com.jobsearchanalytics.model.JobApplication;
import com.jobsearchanalytics.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository repository;

    @Override
    public JobApplicationResponse create(CreateJobApplicationRequest request) {
        JobApplication job = new JobApplication();

        mapCreateRequest(request, job);

        JobApplication saved = repository.save(job);
        return toResponse(saved);
    }

    @Override
    public List<JobApplicationResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public JobApplicationResponse getById(Long id) {
        JobApplication job = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found with id: " + id));

        return toResponse(job);
    }

    @Override
    public JobApplicationResponse update(Long id, UpdateJobApplicationRequest request) {
        JobApplication job = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found with id: " + id));

        mapUpdateRequest(request, job);

        JobApplication updated = repository.save(job);
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Job not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // -----------------------------
    // MAPPING HELPERS
    // -----------------------------

    private void mapCreateRequest(CreateJobApplicationRequest request, JobApplication job) {
        job.setJobTitle(request.jobTitle());
        job.setCompanyName(request.companyName());
        job.setLocation(request.location());
        job.setWorkType(request.workType());
        job.setEmploymentType(request.employmentType());
        job.setSalaryRange(request.salaryRange());
        job.setSource(request.source());
        job.setStatus(request.status());
        job.setDateApplied(request.dateApplied());
        job.setStatusDate(request.statusDate());
        job.setJobUrl(request.jobUrl());
        job.setNotes(request.notes());

        job.setRecruiterName(request.recruiterName());
        job.setRecruiterEmail(request.recruiterEmail());
        job.setReferral(request.referral());
        job.setIndustry(request.industry());
    }

    private void mapUpdateRequest(UpdateJobApplicationRequest request, JobApplication job) {

        if (request.jobTitle() != null) job.setJobTitle(request.jobTitle());
        if (request.companyName() != null) job.setCompanyName(request.companyName());
        if (request.location() != null) job.setLocation(request.location());
        if (request.workType() != null) job.setWorkType(request.workType());
        if (request.employmentType() != null) job.setEmploymentType(request.employmentType());
        if (request.salaryRange() != null) job.setSalaryRange(request.salaryRange());
        if (request.source() != null) job.setSource(request.source());
        if (request.status() != null) job.setStatus(request.status());
        if (request.dateApplied() != null) job.setDateApplied(request.dateApplied());
        if (request.statusDate() != null) job.setStatusDate(request.statusDate());
        if (request.jobUrl() != null) job.setJobUrl(request.jobUrl());
        if (request.notes() != null) job.setNotes(request.notes());

        if (request.recruiterName() != null) job.setRecruiterName(request.recruiterName());
        if (request.recruiterEmail() != null) job.setRecruiterEmail(request.recruiterEmail());
        if (request.referral() != null) job.setReferral(request.referral());
        if (request.industry() != null) job.setIndustry(request.industry());
    }

    private JobApplicationResponse toResponse(JobApplication job) {
        return new JobApplicationResponse(
                job.getId(),
                job.getJobTitle(),
                job.getCompanyName(),
                job.getLocation(),
                job.getWorkType(),
                job.getEmploymentType(),
                job.getSalaryRange(),
                job.getSource(),
                job.getStatus(),
                job.getDateApplied(),
                job.getStatusDate(),
                job.getJobUrl(),
                job.getNotes(),
                job.getRecruiterName(),
                job.getRecruiterEmail(),
                job.getReferral(),
                job.getIndustry(),
                job.getCreatedAt(),
                job.getUpdatedAt()
        );
    }
}
