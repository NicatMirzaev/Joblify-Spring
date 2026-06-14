package com.nijad.joblify.service;

import com.nijad.joblify.entity.JobListing;
import com.nijad.joblify.repository.JobListingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobListingServiceTest {

    @Mock
    private JobListingRepository jobListingRepository;

    @InjectMocks
    private JobListingService jobListingService;

    @Test
    void saveJobListing_ShouldReturnSavedJobListing() {
        JobListing jobListing = new JobListing();
        jobListing.setTitle("Software Engineer");
        
        when(jobListingRepository.save(any(JobListing.class))).thenReturn(jobListing);

        JobListing savedJobListing = jobListingService.saveJobListing(jobListing);

        assertNotNull(savedJobListing);
        assertEquals("Software Engineer", savedJobListing.getTitle());
        verify(jobListingRepository, times(1)).save(jobListing);
    }

    @Test
    void getAllJobListings_ShouldReturnListOfJobListings() {
        JobListing job1 = new JobListing();
        job1.setTitle("Backend Developer");
        JobListing job2 = new JobListing();
        job2.setTitle("Frontend Developer");

        when(jobListingRepository.findAll()).thenReturn(Arrays.asList(job1, job2));

        List<JobListing> jobListings = jobListingService.getAllJobListings();

        assertNotNull(jobListings);
        assertEquals(2, jobListings.size());
        verify(jobListingRepository, times(1)).findAll();
    }

    @Test
    void getJobListingById_ShouldReturnJobListing_WhenIdExists() {
        Long id = 1L;
        JobListing jobListing = new JobListing();
        jobListing.setId(id);
        jobListing.setTitle("Full Stack Developer");

        when(jobListingRepository.findById(id)).thenReturn(Optional.of(jobListing));

        JobListing foundJobListing = jobListingService.getJobListingById(id);

        assertNotNull(foundJobListing);
        assertEquals(id, foundJobListing.getId());
        assertEquals("Full Stack Developer", foundJobListing.getTitle());
        verify(jobListingRepository, times(1)).findById(id);
    }
}
