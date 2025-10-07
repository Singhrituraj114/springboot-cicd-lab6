package com.example.springbootcicd.repository;

import com.example.springbootcicd.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Book Repository - Database operations for books
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    List<Book> findByCategory(String category);
    
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
    
    List<Book> findByStockGreaterThan(Integer stock);
}
