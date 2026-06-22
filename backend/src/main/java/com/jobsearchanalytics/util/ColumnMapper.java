package com.jobsearchanalytics.util;

import java.util.HashMap;
import java.util.Map;

public class ColumnMapper {

    private static final Map<String, String> MAP = new HashMap<>();

    static {

        // Core fields
        MAP.put("jobtitle", "jobTitle");
        MAP.put("job_title", "jobTitle");

        MAP.put("companyname", "companyName");
        MAP.put("company_name", "companyName");

        MAP.put("dateapplied", "dateApplied");
        MAP.put("date_applied", "dateApplied");

        MAP.put("employmenttype", "employmentType");
        MAP.put("employment_type", "employmentType");
        MAP.put("position", "employmentType");

        MAP.put("salaryrange", "salaryRange");
        MAP.put("salary_range", "salaryRange");

        MAP.put("worktype", "workType");
        MAP.put("work_type", "workType");

        MAP.put("joburl", "jobUrl");
        MAP.put("job_url", "jobUrl");
        MAP.put("link", "jobUrl");

        MAP.put("statusdate", "statusDate");
        MAP.put("status_date", "statusDate");

        // passthrough fields
        MAP.put("location", "location");
        MAP.put("status", "status");
        MAP.put("source", "source");
        MAP.put("industry", "industry");
        MAP.put("recruiteremail", "recruiterEmail");
        MAP.put("recruitername", "recruiterName");
        MAP.put("notes", "notes");
    }

    public static String normalize(String key) {
        if (key == null) return null;
        return MAP.getOrDefault(key.trim().toLowerCase(), key);
    }
}
