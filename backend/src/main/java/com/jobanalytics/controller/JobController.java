package com.jobanalytics.controller;

import com.jobanalytics.model.JobApplication;
import com.jobanalytics.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    @GetMapping
    public List<JobApplication> getAll() {
        return service.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobApplication getById(@PathVariable UUID id) {
        return service.getJob(id);
    }

    @PostMapping
    public JobApplication create(@RequestBody JobApplication job) {
        return service.createJob(job);
    }

    @PutMapping("/{id}")
    public JobApplication update(@PathVariable UUID id,
                                 @RequestBody JobApplication job) {
        return service.updateJob(id, job);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteJob(id);
    }
}
