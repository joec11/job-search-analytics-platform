package com.jobsearchanalytics.service;

import com.jobsearchanalytics.dto.request.*;
import com.jobsearchanalytics.dto.response.JobApplicationResponse;

import java.util.List;

public interface JobApplicationService {

    JobApplicationResponse create(CreateJobApplicationRequest request);

    List<JobApplicationResponse> getAll();

    JobApplicationResponse getById(Long id);

    JobApplicationResponse update(Long id, UpdateJobApplicationRequest request);

    void delete(Long id);
}
