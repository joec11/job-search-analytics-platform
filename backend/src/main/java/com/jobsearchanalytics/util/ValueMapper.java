package com.jobsearchanalytics.util;

import com.jobsearchanalytics.model.ApplicationStatus;
import com.jobsearchanalytics.model.EmploymentType;
import com.jobsearchanalytics.model.JobSource;
import com.jobsearchanalytics.model.WorkType;

public class ValueMapper {

    // -----------------------------
    // EMPLOYMENT TYPE
    // -----------------------------
    public static EmploymentType mapEmploymentType(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return switch (value.trim().toLowerCase()) {

            case "full-time", "full time", "ft" ->
                    EmploymentType.FULL_TIME;

            case "part-time", "part time", "pt" ->
                    EmploymentType.PART_TIME;

            case "contract", "contractor" ->
                    EmploymentType.CONTRACT;

            case "internship", "intern" ->
                    EmploymentType.INTERNSHIP;

            case "temporary", "temp" ->
                    EmploymentType.TEMPORARY;

            default ->
                    EmploymentType.UNKNOWN;
        };
    }

    // -----------------------------
    // WORK TYPE
    // -----------------------------
    public static WorkType mapWorkType(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return switch (value.trim().toLowerCase()) {

            case "remote" ->
                    WorkType.REMOTE;

            case "hybrid" ->
                    WorkType.HYBRID;

            case "on-site", "onsite", "on site" ->
                    WorkType.ON_SITE;

            default ->
                    WorkType.UNKNOWN;
        };
    }

    // -----------------------------
    // APPLICATION STATUS
    // -----------------------------
    public static ApplicationStatus mapStatus(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        try {
            return ApplicationStatus.valueOf(
                    value.trim()
                            .toUpperCase()
                            .replace(" ", "_")
            );
        } catch (Exception e) {
            return null;
        }
    }

    // -----------------------------
    // JOB SOURCE
    // -----------------------------
    public static JobSource mapSource(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        try {
            return JobSource.valueOf(
                    value.trim()
                            .toUpperCase()
                            .replace(" ", "_")
            );
        } catch (Exception e) {
            return null;
        }
    }
}
