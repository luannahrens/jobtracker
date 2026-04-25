package com.example.jobtracker.controller;

import com.example.jobtracker.model.JobApplication;
import com.example.jobtracker.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/applications")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public String listApplications(@RequestParam(required = false) String status, Model model) {
        model.addAttribute("applications", service.getAll(status));
        model.addAttribute("selectedStatus", status);
        return "index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("jobApplication", new JobApplication());
        model.addAttribute("formAction", "/applications");
        return "application-form";
    }

    @PostMapping
    public String createApplication(@Valid @ModelAttribute JobApplication jobApplication,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/applications");
            return "application-form";
        }

        service.save(jobApplication);
        return "redirect:/applications";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        JobApplication app = service.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid application id: " + id));
        model.addAttribute("jobApplication", app);
        model.addAttribute("formAction", "/applications/" + id);
        return "application-form";
    }

    @PostMapping("/{id}")
    public String updateApplication(@PathVariable Long id,
                                    @Valid @ModelAttribute JobApplication jobApplication,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/applications/" + id);
            return "application-form";
        }

        jobApplication.setId(id);
        service.update(jobApplication);
        return "redirect:/applications";
    }

    @PostMapping("/{id}/delete")
    public String deleteApplication(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/applications";
    }
}