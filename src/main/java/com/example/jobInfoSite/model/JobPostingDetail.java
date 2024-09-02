package com.example.jobInfoSite.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobPostingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String jobTitle;
    private String location;
    private String employmentType;
    private int positionCount;
    private String salary;
    private String companyLogoPath;

    @ElementCollection
    @CollectionTable(name = "job_posting_detail_additional_image_paths", joinColumns = @JoinColumn(name = "job_posting_detail_id"))
    @Column(name = "additional_image_paths")
    private List<String> jobPostingImagePaths = new ArrayList<>();

    // Lombok will generate getters and setters for all fields
}