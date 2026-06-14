package com.nijad.joblify.scheduler;

import com.nijad.joblify.repository.JobListingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobStatsScheduler {

    @Autowired
    private JobListingRepository jobListingRepository;

    @Scheduled(fixedRate = 60000)
    public void logJobStats() {
        long count = jobListingRepository.count();
        log.info("Total job listings available: {}", count);
    }
}
