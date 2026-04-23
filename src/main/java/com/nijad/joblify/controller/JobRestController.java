package com.nijad.joblify.controller;

import com.nijad.joblify.entity.Company;
import com.nijad.joblify.entity.JobListing;
import com.nijad.joblify.service.CompanyService;
import com.nijad.joblify.service.JobListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JobRestController {

    private final JobListingService jobListingService;
    private final CompanyService companyService;

    @GetMapping("/jobs")
    public List<JobListing> getAllJobs() {
        return jobListingService.getAllJobListings();
    }

    @GetMapping("/jobs/{id}")
    public JobListing getJobById(@PathVariable Long id) {
        return jobListingService.getJobListingById(id);
    }

    @GetMapping("/companies")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }
}
