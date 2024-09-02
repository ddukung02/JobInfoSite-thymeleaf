package com.example.jobInfoSite.service;

import com.example.jobInfoSite.model.JobSeekerDetail;
import com.example.jobInfoSite.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    public List<JobSeekerDetail> getAllJobSeekers() {
        return jobSeekerRepository.findAll();
    }

    public void saveJobSeeker(JobSeekerDetail jobSeekerDetail) {
        jobSeekerRepository.save(jobSeekerDetail);
    }

    public JobSeekerDetail getJobSeekerById(Long id) {
        return jobSeekerRepository.findById(id).orElse(null);
    }
}
