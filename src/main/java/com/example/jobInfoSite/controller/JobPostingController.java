package com.example.jobInfoSite.controller;

import com.example.jobInfoSite.model.JobPostingDetail;
import com.example.jobInfoSite.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/jobs")
public class JobPostingController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private JobPostingService jobPostingService;

    @GetMapping
    public String listJobPostings(Model model) {
        model.addAttribute("jobPostings", jobPostingService.getAllJobPostings());
        return "jobPosting/jobs";
    }

    @GetMapping("/new")
    public String newJobPostingForm(Model model) {
        model.addAttribute("jobForm", new JobPostingDetail());
        return "jobPosting/job_form";
    }

    @PostMapping
    public String saveJobPosting(@ModelAttribute("jobForm") JobPostingDetail jobForm,
                                 @RequestParam("companyLogo") MultipartFile companyLogo,
                                 @RequestParam("jobPostingImage") MultipartFile[] jobPostingImages) {
        String userId = "aaaa"; // 사용자 아이디를 동적으로 가져오도록 변경
        File uploadDir = new File(uploadPath + "/" + userId);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        if (!companyLogo.isEmpty()) {
            String logoPath = companyLogo.getOriginalFilename();
            try {
                companyLogo.transferTo(new File(uploadDir + "/" + logoPath));
                jobForm.setCompanyLogoPath("/" + userId + "/" + logoPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (MultipartFile jobPostingImage : jobPostingImages) {
            if (!jobPostingImage.isEmpty()) {
                String imagePath = jobPostingImage.getOriginalFilename();
                try {
                    jobPostingImage.transferTo(new File(uploadDir + "/" + imagePath));
                    jobForm.getJobPostingImagePaths().add("/" + userId + "/" + imagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        jobPostingService.saveJobPosting(jobForm);
        return "redirect:/jobs";
    }

    @PostMapping("/delete")
    public String deleteJobPosting(@RequestParam Long id) {
        jobPostingService.deleteJobPosting(id);
        return "redirect:/jobs";
    }

    @GetMapping("/{id}")
    public String viewJobPosting(@PathVariable Long id, Model model) {
        JobPostingDetail jobPostingDetail = jobPostingService.getJobPostingById(id);
        model.addAttribute("jobPostingDetail", jobPostingDetail);
        return "jobPosting/job_detail";
    }
}
