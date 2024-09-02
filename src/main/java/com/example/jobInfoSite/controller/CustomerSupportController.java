package com.example.jobInfoSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class CustomerSupportController {

    @GetMapping("/support")
    public String customerSupport(Model model) {
        // 필요한 데이터를 모델에 추가합니다.
        model.addAttribute("name", "뚜꿍");
        model.addAttribute("email", "ddukung@example.com");
        model.addAttribute("phone", "010-1234-5678");

        return "support/customer-support"; // Thymeleaf 템플릿 파일의 경로를 반환합니다.
    }
}
