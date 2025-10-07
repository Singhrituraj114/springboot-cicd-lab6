package com.example.springbootcicd.config;

import com.example.springbootcicd.model.Book;
import com.example.springbootcicd.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Data Initializer - Populates sample books on startup
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {
        if (bookRepository.count() == 0) {
            System.out.println("📚 Initializing bookstore with sample data...");
            
            // Technology Books
            bookRepository.save(createBook(
                "Clean Code",
                "Robert C. Martin",
                "978-0132350884",
                new BigDecimal("39.99"),
                "A Handbook of Agile Software Craftsmanship. Learn to write clean, maintainable code.",
                "Technology",
                "https://m.media-amazon.com/images/I/41xShlnTZTL._SY445_SX342_.jpg",
                50
            ));

            bookRepository.save(createBook(
                "The Pragmatic Programmer",
                "Andy Hunt & Dave Thomas",
                "978-0135957059",
                new BigDecimal("44.99"),
                "Your Journey to Mastery, 20th Anniversary Edition.",
                "Technology",
                "https://m.media-amazon.com/images/I/51W1sBPO7tL._SY445_SX342_.jpg",
                30
            ));

            bookRepository.save(createBook(
                "Design Patterns",
                "Gang of Four",
                "978-0201633610",
                new BigDecimal("49.99"),
                "Elements of Reusable Object-Oriented Software.",
                "Technology",
                "https://m.media-amazon.com/images/I/51szD9HC9pL._SY445_SX342_.jpg",
                25
            ));

            bookRepository.save(createBook(
                "Introduction to Algorithms",
                "Thomas H. Cormen",
                "978-0262033848",
                new BigDecimal("89.99"),
                "The comprehensive guide to algorithms.",
                "Technology",
                "https://m.media-amazon.com/images/I/61Pgdn8Ys-L._SY466_.jpg",
                15
            ));

            bookRepository.save(createBook(
                "You Don't Know JS",
                "Kyle Simpson",
                "978-1491904244",
                new BigDecimal("29.99"),
                "Up & Going - JavaScript fundamentals.",
                "Technology",
                "https://m.media-amazon.com/images/I/41T5H8u7fUL._SY445_SX342_.jpg",
                40
            ));

            // Fiction Books
            bookRepository.save(createBook(
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                "978-0743273565",
                new BigDecimal("14.99"),
                "A classic American novel set in the Jazz Age.",
                "Fiction",
                "https://m.media-amazon.com/images/I/41iers+RJNL._SY445_SX342_.jpg",
                100
            ));

            bookRepository.save(createBook(
                "To Kill a Mockingbird",
                "Harper Lee",
                "978-0061120084",
                new BigDecimal("16.99"),
                "A gripping tale of racial injustice and childhood innocence.",
                "Fiction",
                "https://m.media-amazon.com/images/I/41j-s9fHJcL._SY445_SX342_.jpg",
                80
            ));

            bookRepository.save(createBook(
                "1984",
                "George Orwell",
                "978-0451524935",
                new BigDecimal("13.99"),
                "A dystopian social science fiction novel.",
                "Fiction",
                "https://m.media-amazon.com/images/I/41E0+A9H4TL._SY445_SX342_.jpg",
                60
            ));

            // Science Books
            bookRepository.save(createBook(
                "A Brief History of Time",
                "Stephen Hawking",
                "978-0553380163",
                new BigDecimal("18.99"),
                "From the Big Bang to Black Holes.",
                "Science",
                "https://m.media-amazon.com/images/I/51kH+CIjKkL._SY445_SX342_.jpg",
                35
            ));

            bookRepository.save(createBook(
                "Sapiens",
                "Yuval Noah Harari",
                "978-0062316110",
                new BigDecimal("24.99"),
                "A Brief History of Humankind.",
                "Science",
                "https://m.media-amazon.com/images/I/41pOTqPDKIL._SY445_SX342_.jpg",
                45
            ));

            bookRepository.save(createBook(
                "The Selfish Gene",
                "Richard Dawkins",
                "978-0199291151",
                new BigDecimal("16.99"),
                "40th Anniversary edition of the groundbreaking work on evolution.",
                "Science",
                "https://m.media-amazon.com/images/I/41UsDffqAhL._SY445_SX342_.jpg",
                30
            ));

            // Business Books
            bookRepository.save(createBook(
                "The Lean Startup",
                "Eric Ries",
                "978-0307887894",
                new BigDecimal("26.99"),
                "How Today's Entrepreneurs Use Continuous Innovation.",
                "Business",
                "https://m.media-amazon.com/images/I/41TiUKFZM9L._SY445_SX342_.jpg",
                55
            ));

            bookRepository.save(createBook(
                "Zero to One",
                "Peter Thiel",
                "978-0804139298",
                new BigDecimal("19.99"),
                "Notes on Startups, or How to Build the Future.",
                "Business",
                "https://m.media-amazon.com/images/I/41vZQz-6yaL._SY445_SX342_.jpg",
                40
            ));

            bookRepository.save(createBook(
                "Good to Great",
                "Jim Collins",
                "978-0066620992",
                new BigDecimal("29.99"),
                "Why Some Companies Make the Leap and Others Don't.",
                "Business",
                "https://m.media-amazon.com/images/I/41NMJvY3SYL._SY445_SX342_.jpg",
                35
            ));

            // Biography Books
            bookRepository.save(createBook(
                "Steve Jobs",
                "Walter Isaacson",
                "978-1451648539",
                new BigDecimal("19.99"),
                "The exclusive biography of Apple's co-founder.",
                "Biography",
                "https://m.media-amazon.com/images/I/41IaZG4-70L._SY445_SX342_.jpg",
                50
            ));

            bookRepository.save(createBook(
                "Elon Musk",
                "Ashlee Vance",
                "978-0062301239",
                new BigDecimal("17.99"),
                "Tesla, SpaceX, and the Quest for a Fantastic Future.",
                "Biography",
                "https://m.media-amazon.com/images/I/51MEm7VE5jL._SY445_SX342_.jpg",
                45
            ));

            System.out.println("✅ Sample data loaded: " + bookRepository.count() + " books!");
        } else {
            System.out.println("📚 Bookstore already has " + bookRepository.count() + " books.");
        }
    }

    private Book createBook(String title, String author, String isbn, BigDecimal price,
                           String description, String category, String imageUrl, Integer stock) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setPrice(price);
        book.setDescription(description);
        book.setCategory(category);
        book.setImageUrl(imageUrl);
        book.setStock(stock);
        return book;
    }
}
