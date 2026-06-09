package com.jobsearchanalytics.service.importer;

import com.jobsearchanalytics.model.JobApplication;

public record ImportRowResult(
        JobApplication job,
        boolean valid,
        String errorMessage,
        int rowNumber
) {}
