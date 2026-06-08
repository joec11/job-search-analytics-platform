package com.jobsearchanalytics.dto.response;

import java.util.Map;

public record ErrorResponse(
        boolean success,
        String message,
        Map<String, String> errors
) {}
