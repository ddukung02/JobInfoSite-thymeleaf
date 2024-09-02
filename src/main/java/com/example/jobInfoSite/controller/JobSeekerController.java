package com.example.jobInfoSite.controller;

import com.example.jobInfoSite.model.JobSeekerDetail;
import com.example.jobInfoSite.service.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jobSeekers")
public class JobSeekerController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @GetMapping
    public String listJobSeekers(Model model) {
        List<JobSeekerDetail> jobSeekers = jobSeekerService.getAllJobSeekers();
        model.addAttribute("jobSeekers", jobSeekers);
        return "jobSeekers/jobSeekers"; // 목록 페이지의 뷰 이름
    }

    @GetMapping("/new")
    public String showAddJobSeekerForm(Model model) {
        model.addAttribute("jobSeekerDetail", new JobSeekerDetail());
        return "jobSeekers/jobSeekerForm"; // 작성 페이지의 뷰 이름
    }

    @PostMapping
    public String addJobSeeker(@ModelAttribute("jobSeekerDetail") JobSeekerDetail jobSeekerDetail) {
        jobSeekerService.saveJobSeeker(jobSeekerDetail);
        return "redirect:/jobSeekers";
    }

    @GetMapping("/{id}")
    public String viewJobSeekerDetail(@PathVariable("id") Long id, Model model) {
        JobSeekerDetail jobSeekerDetail = jobSeekerService.getJobSeekerById(id);
        if (jobSeekerDetail != null) {
            model.addAttribute("jobSeeker", jobSeekerDetail);
            return "jobSeekers/jobSeekerDetailView"; // 상세보기 페이지의 뷰 이름
        } else {
            // 에러 페이지로 리디렉션하거나 에러 메시지를 반환하는 로직 추가
            return "error/404"; // 예시로 404 페이지로 리디렉션
        }
    }
}
