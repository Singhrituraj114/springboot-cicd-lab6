package com.example.springbootcicd.controller;

import com.example.springbootcicd.model.Book;
import com.example.springbootcicd.model.Order;
import com.example.springbootcicd.service.BookService;
import com.example.springbootcicd.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST API Controller for the Online Bookstore
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppController {

    private final BookService bookService;
    private final OrderService orderService;

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("application", "Online Bookstore");
        response.put("version", "2.0.0");
        response.put("description", "Full-featured e-commerce bookstore with CI/CD pipeline");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", "running");
        response.put("features", new String[]{
            "Book Catalog",
            "Shopping Cart",
            "Order Management",
            "Search & Filter",
            "REST API"
        });
        return response;
    }

    @GetMapping("/hello")
    public Map<String, String> hello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to Online Bookstore!");
        response.put("deployed", "via Jenkins CI/CD Pipeline on Kubernetes");
        response.put("version", "2.0.0");
        return response;
    }

    @GetMapping("/info")
    public Map<String, Object> info() {
        Map<String, Object> response = new HashMap<>();
        response.put("application", "Online Bookstore - E-Commerce Platform");
        response.put("description", "Complete bookstore application with CI/CD pipeline");
        response.put("technologies", new String[]{
            "Java 17",
            "Spring Boot 3.2",
            "Spring Data JPA",
            "Thymeleaf",
            "H2 Database",
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

    @GetMapping("/status")
    public Map<String, Object> status() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "healthy");
        response.put("version", "2.0.0");
        response.put("bookCount", bookService.getAllBooks().size());
        response.put("availableBooks", bookService.getAvailableBooks().size());
        response.put("categories", bookService.getAllCategories().size());
        response.put("deployment", "Kubernetes with 3 replicas");
        response.put("timestamp", LocalDateTime.now().toString());
        return response;
    }

    // REST API Endpoints for Books
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String q) {
        return ResponseEntity.ok(bookService.searchBooks(q));
    }

    @GetMapping("/books/category/{category}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable String category) {
        return ResponseEntity.ok(bookService.getBooksByCategory(category));
    }

    // REST API Endpoints for Orders
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
