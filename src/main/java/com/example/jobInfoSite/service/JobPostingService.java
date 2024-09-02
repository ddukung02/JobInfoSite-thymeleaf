package com.example.jobInfoSite.service;

import com.example.jobInfoSite.model.JobPostingDetail;
import com.example.jobInfoSite.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPostingService {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    public List<JobPostingDetail> getAllJobPostings() {
        return jobPostingRepository.findAll();
    }

    public void saveJobPosting(JobPostingDetail jobPostingDetail) {
        jobPostingRepository.save(jobPostingDetail);
    }

    public void deleteJobPosting(Long id) {
        jobPostingRepository.deleteById(id);
    }

    public JobPostingDetail getJobPostingById(Long id) {
        Optional<JobPostingDetail> optionalJobPostingDetail = jobPostingRepository.findById(id);
        return optionalJobPostingDetail.orElse(null);
    }
}
