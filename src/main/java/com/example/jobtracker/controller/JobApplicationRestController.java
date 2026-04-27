package com.example.jobtracker.controller;

import com.example.jobtracker.model.JobApplication;
import com.example.jobtracker.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationRestController {

    private final JobApplicationService service;

    public JobApplicationRestController(JobApplicationService service) {
        this.service = service;
    }

    /**
     * Get all job applications, optionally filtered by status
     *
     * @param status Optional status filter parameter
     * @return List of job applications
     */
    @GetMapping
    public ResponseEntity<List<JobApplication>> getAllApplications(@RequestParam(required = false) String status) {
        List<JobApplication> applications = service.getAll(status);
        return ResponseEntity.ok(applications);
    }

    /**
     * Get a specific job application by ID
     *
     * @param id The application ID
     * @return The job application or 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getApplicationById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new job application
     *
     * @param jobApplication The job application data
     * @return The created application with 201 Created status
     */
    @PostMapping
    public ResponseEntity<JobApplication> createApplication(@Valid @RequestBody JobApplication jobApplication) {
        service.save(jobApplication);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobApplication);
    }

    /**
     * Update an existing job application
     *
     * @param id The application ID
     * @param jobApplication The updated application data
     * @return The updated application or 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateApplication(@PathVariable Long id,
                                                             @Valid @RequestBody JobApplication jobApplication) {
        if (!service.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        jobApplication.setId(id);
        service.update(jobApplication);
        return ResponseEntity.ok(jobApplication);
    }

    /**
     * Delete a job application
     *
     * @param id The application ID
     * @return 204 No Content if successful, 404 Not Found if application doesn't exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        if (!service.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
