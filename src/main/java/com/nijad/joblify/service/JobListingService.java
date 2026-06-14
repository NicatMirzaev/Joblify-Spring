package com.nijad.joblify.service;

import com.nijad.joblify.entity.JobListing;
import com.nijad.joblify.repository.JobListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobListingService {

    private final JobListingRepository jobListingRepository;

    @org.springframework.cache.annotation.CacheEvict(value = "jobs", allEntries = true)
    public JobListing saveJobListing(JobListing jobListing) {
        return jobListingRepository.save(jobListing);
    }

    @org.springframework.cache.annotation.Cacheable("jobs")
    public List<JobListing> getAllJobListings() {
        return jobListingRepository.findAll();
    }

    public JobListing getJobListingById(Long id) {
        return jobListingRepository.findById(id).orElse(null);
    }
}
