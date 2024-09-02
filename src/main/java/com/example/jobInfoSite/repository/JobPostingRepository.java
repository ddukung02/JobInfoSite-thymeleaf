package com.example.jobInfoSite.repository;

import com.example.jobInfoSite.model.JobPostingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingRepository extends JpaRepository<JobPostingDetail, Long> {
}
