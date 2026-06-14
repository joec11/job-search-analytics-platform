package com.jobsearchanalytics.util;

public class ColumnMapper {

    public static String map(String input) {

        if (input == null) return null;

        String normalized = input.trim().toLowerCase();

        return switch (normalized) {

            // core identity
            case "job title", "title" -> "jobTitle";
            case "company", "company name" -> "companyName";

            // mapping important for your dataset
            case "position", "job type", "employment type" -> "employmentType";

            case "location" -> "location";
            case "work type" -> "workType";
            case "salary range" -> "salaryRange";

            case "date applied" -> "dateApplied";
            case "status" -> "status";
            case "status date" -> "statusDate";

            case "link", "url", "job link" -> "jobUrl";

            case "source" -> "source";

            default -> normalized;
        };
    }
}
