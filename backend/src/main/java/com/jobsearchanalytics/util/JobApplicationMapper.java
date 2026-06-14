package com.jobsearchanalytics.util;

import com.jobsearchanalytics.model.JobApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class JobApplicationMapper {

    private static final DateTimeFormatter ISO_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter US_FORMAT =
            DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public static JobApplication fromRow(Map<String, String> row) {

        JobApplication job = new JobApplication();

        job.setJobTitle(row.get("jobTitle"));
        job.setCompanyName(row.get("companyName"));
        job.setLocation(row.get("location"));

        job.setEmploymentType(
                ValueMapper.mapEmploymentType(
                        row.get("employmentType")
                )
        );

        job.setWorkType(
                ValueMapper.mapWorkType(
                        row.get("workType")
                )
        );

        job.setSalaryRange(row.get("salaryRange"));
        job.setJobUrl(row.get("jobUrl"));

        job.setStatus(
                ValueMapper.mapStatus(
                        row.get("status")
                )
        );

        job.setSource(
                ValueMapper.mapSource(
                        row.get("source")
                )
        );

        job.setDateApplied(
                parseDate(
                        row.get("dateApplied")
                )
        );

        job.setStatusDate(
                parseDate(
                        row.get("statusDate")
                )
        );

        return job;
    }

    private static LocalDate parseDate(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        String date = value.trim();

        try {
            return LocalDate.parse(date, ISO_FORMAT);
        } catch (Exception ignored) {
        }

        try {
            return LocalDate.parse(date, US_FORMAT);
        } catch (Exception ignored) {
        }

        return null;
    }
}
