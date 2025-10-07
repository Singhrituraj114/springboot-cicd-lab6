package com.example.springbootcicd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for Spring Boot CI/CD Application
 */
@SpringBootApplication
public class SpringBootCicdApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCicdApplication.class, args);
        System.out.println("========================================");
        System.out.println("Spring Boot Application Started!");
        System.out.println("Access at: http://localhost:8080");
        System.out.println("Health Check: http://localhost:8080/actuator/health");
        System.out.println("========================================");
    }
}
