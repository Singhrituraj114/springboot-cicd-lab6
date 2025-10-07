package com.example.springbootcicd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller for the Spring Boot CI/CD Application
 */
@RestController
@RequestMapping("/api")
public class AppController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to Spring Boot CI/CD Application!");
        response.put("version", "1.0.0");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", "running");
        return response;
    }

    @GetMapping("/hello")
    public Map<String, String> hello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from Kubernetes!");
        response.put("deployed", "via Jenkins CI/CD Pipeline");
        return response;
    }

    @GetMapping("/info")
    public Map<String, Object> info() {
        Map<String, Object> response = new HashMap<>();
        response.put("application", "Spring Boot CI/CD Demo");
        response.put("description", "Complete CI/CD pipeline with Jenkins, Docker, and Kubernetes");
        response.put("technologies", new String[]{
            "Java 17",
            "Spring Boot 3.2",
            "Maven",
            "Docker",
            "Kubernetes",
            "Jenkins",
            "Terraform",
            "Ansible",
            "AWS EKS"
        });
        response.put("timestamp", LocalDateTime.now().toString());
        return response;
    }
}
