package com.jobsearchanalytics.service;

import com.jobsearchanalytics.dto.request.*;
import com.jobsearchanalytics.dto.response.JobApplicationResponse;
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
        return null;
    }

    @Override
    public List<JobApplicationResponse> getAll() {
        return List.of();
    }

    @Override
    public JobApplicationResponse getById(Long id) {
        return null;
    }

    @Override
    public JobApplicationResponse update(Long id, UpdateJobApplicationRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }
}
