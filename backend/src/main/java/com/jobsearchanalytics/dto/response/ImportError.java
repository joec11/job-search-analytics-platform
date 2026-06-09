package com.jobsearchanalytics.dto.response;

public record ImportError(
        int rowNumber,
        String message
) {}
