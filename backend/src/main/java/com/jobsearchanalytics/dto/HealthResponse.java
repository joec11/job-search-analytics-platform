package com.jobsearchanalytics.dto;

import java.time.Instant;

public record HealthResponse(
    String status,
    Instant timestamp
) {
}
