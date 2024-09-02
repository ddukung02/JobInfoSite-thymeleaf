package com.example.jobInfoSite.controller;

import com.example.jobInfoSite.model.User;
import com.example.jobInfoSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "account/register";  // 경로 수정
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registerUserAccountForm(User user) {
        userService.save(user);
        return "redirect:/account/login";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUserAccountJson(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "account/login";  // 경로 수정
    }
}
