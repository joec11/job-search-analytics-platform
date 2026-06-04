package com.jobanalytics.dto;

public class AnalyticsSummary {

    private Integer totalApplications;
    private Integer uniqueCompanies;
    private Integer uniqueLocations;
    private Double averageSalary;

    public AnalyticsSummary(Integer totalApplications,
                            Integer uniqueCompanies,
                            Integer uniqueLocations,
                            Double averageSalary) {
        this.totalApplications = totalApplications;
        this.uniqueCompanies = uniqueCompanies;
        this.uniqueLocations = uniqueLocations;
        this.averageSalary = averageSalary;
    }

    // getters
}
