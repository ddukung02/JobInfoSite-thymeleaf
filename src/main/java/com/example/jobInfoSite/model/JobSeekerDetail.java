package com.example.jobInfoSite.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "job_seeker_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "desired_job_title")
    private String desiredJobTitle;

    @Column(name = "location")
    private String location;

    @Column(name = "employment_type")
    private String employmentType;

    @Column(name = "expected_salary")
    private String expectedSalary;

    @Column(name = "skills")
    private String skills;
}
