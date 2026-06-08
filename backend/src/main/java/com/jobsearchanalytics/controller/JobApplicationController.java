package com.jobsearchanalytics.controller;

import com.jobsearchanalytics.dto.request.CreateJobApplicationRequest;
import com.jobsearchanalytics.dto.response.ApiResponse;
import com.jobsearchanalytics.dto.response.JobApplicationResponse;
import com.jobsearchanalytics.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<JobApplicationResponse> create(
            @Valid @RequestBody CreateJobApplicationRequest request) {

        return new ApiResponse<>(
                true,
                "Job created successfully",
                service.create(request)
        );
    }
}
