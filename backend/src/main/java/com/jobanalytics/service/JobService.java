package com.jobanalytics.service;

import com.jobanalytics.model.JobApplication;
import com.jobanalytics.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobService {

    private final JobApplicationRepository repository;

    public JobService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public List<JobApplication> getAllJobs() {
        return repository.findAll();
    }

    public JobApplication getJob(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public JobApplication createJob(JobApplication job) {
        return repository.save(job);
    }

    public JobApplication updateJob(UUID id, JobApplication updated) {
        updated.setId(id);
        return repository.save(updated);
    }

    public void deleteJob(UUID id) {
        repository.deleteById(id);
    }
}
