package com.jobsearchanalytics.util;

import java.util.Map;

public final class ColumnMappingRegistry {

    private ColumnMappingRegistry() {
    }

    public static final Map<String, String> HEADER_MAPPINGS = Map.ofEntries(

            // Job Title
            Map.entry("job title", "jobTitle"),
            Map.entry("title", "jobTitle"),
            Map.entry("position", "jobTitle"),
            Map.entry("role", "jobTitle"),

            // Company
            Map.entry("company", "companyName"),
            Map.entry("company name", "companyName"),
            Map.entry("employer", "companyName"),
            Map.entry("organization", "companyName"),

            // Location
            Map.entry("location", "location"),
            Map.entry("city", "location"),

            // Source
            Map.entry("source", "source"),
            Map.entry("job source", "source"),

            // Status
            Map.entry("status", "status"),
            Map.entry("application status", "status"),

            // Work Type
            Map.entry("work type", "workType"),

            // Employment Type
            Map.entry("employment type", "employmentType"),

            // Salary
            Map.entry("salary", "salaryRange"),
            Map.entry("salary range", "salaryRange"),

            // Dates
            Map.entry("date applied", "dateApplied"),
            Map.entry("application date", "dateApplied"),
            Map.entry("status date", "statusDate")
    );
}
