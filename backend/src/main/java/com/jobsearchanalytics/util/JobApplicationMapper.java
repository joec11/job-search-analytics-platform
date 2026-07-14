package com.jobsearchanalytics.util;

import com.jobsearchanalytics.model.JobApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class JobApplicationMapper {

    private static final DateTimeFormatter US_FORMAT =
            DateTimeFormatter.ofPattern("MM/dd/yyyy");


    public static JobApplication fromRow(Map<String, String> row) {

        JobApplication job = new JobApplication();

        job.setJobTitle(
                clean(row.get("jobTitle"))
        );

        job.setCompanyName(
                clean(row.get("companyName"))
        );

        job.setLocation(
                clean(row.get("location"))
        );


        job.setEmploymentType(
                ValueMapper.mapEmploymentType(
                        clean(row.get("employmentType"))
                )
        );


        job.setWorkType(
                ValueMapper.mapWorkType(
                        clean(row.get("workType"))
                )
        );


        job.setSalaryRange(
                clean(row.get("salaryRange"))
        );


        job.setJobUrl(
                clean(row.get("jobUrl"))
        );


        job.setStatus(
                ValueMapper.mapStatus(
                        clean(row.get("status"))
                )
        );


        job.setSource(
                ValueMapper.mapSource(
                        clean(row.get("source"))
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


        job.setNotes(
                clean(row.get("notes"))
        );


        job.setRecruiterName(
                clean(row.get("recruiterName"))
        );


        job.setRecruiterEmail(
                clean(row.get("recruiterEmail"))
        );


        job.setIndustry(
                clean(row.get("industry"))
        );


        job.setReferral(
                parseBoolean(
                        row.get("referral")
                )
        );


        return job;
    }


    private static String clean(String value) {

        if (value == null) {
            return null;
        }

        String trimmed = value.trim();

        return trimmed.isEmpty()
                ? null
                : trimmed;
    }


    private static LocalDate parseDate(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        String date = value.trim();


        try {
            return LocalDate.parse(date);

        } catch (Exception ignored) {
        }


        try {
            return LocalDate.parse(date, US_FORMAT);

        } catch (Exception ignored) {
        }


        return null;
    }


    private static Boolean parseBoolean(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return switch (value.trim().toLowerCase()) {

            case "true", "yes", "y", "1" ->
                    true;

            case "false", "no", "n", "0" ->
                    false;

            default ->
                    null;
        };
    }
}
