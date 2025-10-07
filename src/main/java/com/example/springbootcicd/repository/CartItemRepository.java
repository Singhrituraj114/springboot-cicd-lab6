package com.example.springbootcicd.repository;

import com.example.springbootcicd.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * CartItem Repository - Database operations for cart items
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    List<CartItem> findBySessionId(String sessionId);
    
    Optional<CartItem> findBySessionIdAndBookId(String sessionId, Long bookId);
    
    void deleteBySessionId(String sessionId);
}
