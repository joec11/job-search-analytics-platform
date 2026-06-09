package com.jobsearchanalytics.dto.response;

import java.util.List;

public record ImportSummary(
        int totalRows,
        int successfulRows,
        int failedRows,
        List<ImportError> errors
) {}
