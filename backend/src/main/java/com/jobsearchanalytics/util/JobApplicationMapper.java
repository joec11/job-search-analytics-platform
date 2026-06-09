package com.jobsearchanalytics.util;

import com.jobsearchanalytics.model.*;

import java.util.Map;

public class JobApplicationMapper {

    public static JobApplication fromRow(Map<String, String> row) {

        JobApplication job = new JobApplication();

        job.setJobTitle(row.get("jobTitle"));
        job.setCompanyName(row.get("companyName"));
        job.setLocation(row.get("location"));

        job.setWorkType(parseEnum(WorkType.class, row.get("workType")));
        job.setEmploymentType(parseEnum(EmploymentType.class, row.get("employmentType")));
        job.setSource(parseEnum(JobSource.class, row.get("source")));
        job.setStatus(parseEnum(ApplicationStatus.class, row.get("status")));

        job.setSalaryRange(row.get("salaryRange"));
        job.setJobUrl(row.get("jobUrl"));
        job.setNotes(row.get("notes"));

        job.setRecruiterName(row.get("recruiterName"));
        job.setRecruiterEmail(row.get("recruiterEmail"));
        job.setReferral(parseBoolean(row.get("referral")));
        job.setIndustry(row.get("industry"));

        return job;
    }

    private static <T extends Enum<T>> T parseEnum(Class<T> enumClass, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        try {
            return Enum.valueOf(
                    enumClass,
                    value.trim().toUpperCase().replace("-", "_").replace(" ", "_")
            );
        } catch (Exception e) {
            return null; // will be caught in service validation
        }
    }

    private static Boolean parseBoolean(String value) {
        if (value == null) return false;
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes");
    }
}
