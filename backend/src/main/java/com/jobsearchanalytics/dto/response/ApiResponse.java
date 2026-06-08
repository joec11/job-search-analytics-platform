package com.jobsearchanalytics.dto.response;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {}
