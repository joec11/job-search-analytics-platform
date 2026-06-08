package com.jobsearchanalytics.controller;

import com.jobsearchanalytics.dto.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public HealthResponse health() {
        return new HealthResponse(
            "Backend is running",
            Instant.now()
        );
    }
}
