package com.jobsearchanalytics.util;

import java.util.HashMap;
import java.util.Map;

public final class ColumnMapper {

    private ColumnMapper() {
        // Utility class
    }

    private static final Map<String, String> MAP = new HashMap<>();

    static {

        // -------------------------
        // Job Title
        // -------------------------
        MAP.put("jobtitle", "jobTitle");
        MAP.put("title", "jobTitle");
        MAP.put("position", "jobTitle");

        // -------------------------
        // Company
        // -------------------------
        MAP.put("company", "companyName");
        MAP.put("companyname", "companyName");
        MAP.put("employer", "companyName");

        // -------------------------
        // Location
        // -------------------------
        MAP.put("location", "location");

        // -------------------------
        // Date Applied
        // -------------------------
        MAP.put("dateapplied", "dateApplied");
        MAP.put("applieddate", "dateApplied");
        MAP.put("applydate", "dateApplied");

        // -------------------------
        // Employment Type
        // -------------------------
        MAP.put("employmenttype", "employmentType");
        MAP.put("jobtype", "employmentType");
        MAP.put("employment", "employmentType");

        // -------------------------
        // Salary
        // -------------------------
        MAP.put("salary", "salaryRange");
        MAP.put("salaryrange", "salaryRange");

        // -------------------------
        // Work Type
        // -------------------------
        MAP.put("worktype", "workType");
        MAP.put("remotetype", "workType");

        // -------------------------
        // Job URL
        // -------------------------
        MAP.put("joburl", "jobUrl");
        MAP.put("url", "jobUrl");
        MAP.put("website", "jobUrl");
        MAP.put("link", "jobUrl");

        // -------------------------
        // Status
        // -------------------------
        MAP.put("status", "status");
        MAP.put("statusdate", "statusDate");

        // -------------------------
        // Source
        // -------------------------
        MAP.put("source", "source");

        // -------------------------
        // Industry
        // -------------------------
        MAP.put("industry", "industry");

        // -------------------------
        // Recruiter
        // -------------------------
        MAP.put("recruitername", "recruiterName");
        MAP.put("recruiteremail", "recruiterEmail");

        // -------------------------
        // Notes
        // -------------------------
        MAP.put("notes", "notes");

        // -------------------------
        // Referral
        // -------------------------
        MAP.put("referral", "referral");
    }

    public static String normalize(String key) {

        if (key == null) {
            return null;
        }

        String normalized = key
                .trim()
                .toLowerCase()
                .replace(" ", "")
                .replace("_", "")
                .replace("-", "")
                .replace(".", "");

        return MAP.getOrDefault(normalized, key.trim());
    }
}
