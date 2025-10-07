package com.example.springbootcicd.repository;

import com.example.springbootcicd.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Order Repository - Database operations for orders
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByCustomerEmailOrderByOrderDateDesc(String email);
    
    List<Order> findByStatus(String status);
}
