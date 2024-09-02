package com.example.jobInfoSite.controller;

import com.example.jobInfoSite.model.User;
import com.example.jobInfoSite.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserApiController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> all(@RequestParam(required = false) String method, @RequestParam(required = false) String text){
        List<User> users = null;

        if ("query".equals(method)){
            users = userRepository.findByUsernameContaining(text);
        } else {
            users = userRepository.findAll();
        }

        return users;
    }
}
