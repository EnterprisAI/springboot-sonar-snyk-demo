package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        String password = "hardcoded_password"; // Sonar issue
        if (password == "hardcoded_password") { // Bug: string comparison
            return "Hello, World!";
        }
        return null; // Sonar issue: possible NPE
    }
}
