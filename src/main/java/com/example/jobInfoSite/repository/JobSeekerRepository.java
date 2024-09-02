package com.example.jobInfoSite.repository;

import com.example.jobInfoSite.model.JobSeekerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeekerDetail, Long> {
}
