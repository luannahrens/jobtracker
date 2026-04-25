package com.example.jobtracker.service;

import com.example.jobtracker.model.JobApplication;
import com.example.jobtracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public List<JobApplication> getAll(String status) {
        if (status != null && !status.isBlank()) {
            return repository.findByStatus(status);
        }
        return repository.findAll();
    }

    public Optional<JobApplication> getById(Long id) {
        return repository.findById(id);
    }

    public void save(JobApplication app) {
        repository.save(app);
    }

    public void update(JobApplication app) {
        repository.update(app);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}