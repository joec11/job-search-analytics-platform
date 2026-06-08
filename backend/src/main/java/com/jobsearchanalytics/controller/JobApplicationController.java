package com.jobsearchanalytics.controller;

import com.jobsearchanalytics.dto.request.CreateJobApplicationRequest;
import com.jobsearchanalytics.dto.request.UpdateJobApplicationRequest;
import com.jobsearchanalytics.dto.response.ApiResponse;
import com.jobsearchanalytics.dto.response.JobApplicationResponse;
import com.jobsearchanalytics.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ApiResponse<List<JobApplicationResponse>> getAll() {
        return new ApiResponse<>(
                true,
                "Jobs retrieved successfully",
                service.getAll()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<JobApplicationResponse> getById(@PathVariable Long id) {
        return new ApiResponse<>(
                true,
                "Job retrieved successfully",
                service.getById(id)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<JobApplicationResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateJobApplicationRequest request) {

        return new ApiResponse<>(
                true,
                "Job updated successfully",
                service.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return new ApiResponse<>(
                true,
                "Job deleted successfully",
                null
        );
    }
}
