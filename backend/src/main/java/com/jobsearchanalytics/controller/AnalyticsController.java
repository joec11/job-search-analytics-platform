package com.jobsearchanalytics.controller;

import com.jobsearchanalytics.dto.AnalyticsSummary;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    @GetMapping("/summary")
    public AnalyticsSummary summary() {
        return new AnalyticsSummary(0, 0, 0, 0.0);
    }

    @GetMapping("/sources")
    public String sources() {
        return "TODO";
    }

    @GetMapping("/salaries")
    public String salaries() {
        return "TODO";
    }

    @GetMapping("/locations")
    public String locations() {
        return "TODO";
    }
}
