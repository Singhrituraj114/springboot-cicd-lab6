package com.example.springbootcicd.controller;

import com.example.springbootcicd.model.Book;
import com.example.springbootcicd.service.BookService;
import com.example.springbootcicd.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Bookstore Web Controller - Handles web pages
 */
@Controller
@RequiredArgsConstructor
public class BookstoreController {

    private final BookService bookService;
    private final CartService cartService;

    private String getSessionId(HttpSession session) {
        String sessionId = (String) session.getAttribute("CART_SESSION_ID");
        if (sessionId == null) {
            sessionId = session.getId();
            session.setAttribute("CART_SESSION_ID", sessionId);
        }
        return sessionId;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        List<Book> books = bookService.getAvailableBooks();
        model.addAttribute("books", books);
        model.addAttribute("categories", bookService.getAllCategories());
        model.addAttribute("cartCount", cartService.getCartItemCount(getSessionId(session)));
        return "index";
    }

    @GetMapping("/books")
    public String allBooks(@RequestParam(required = false) String category,
                          @RequestParam(required = false) String search,
                          Model model,
                          HttpSession session) {
        List<Book> books;
        
        if (search != null && !search.isEmpty()) {
            books = bookService.searchBooks(search);
        } else if (category != null && !category.isEmpty()) {
            books = bookService.getBooksByCategory(category);
        } else {
            books = bookService.getAllBooks();
        }
        
        model.addAttribute("books", books);
        model.addAttribute("categories", bookService.getAllCategories());
        model.addAttribute("selectedCategory", category);
        model.addAttribute("searchQuery", search);
        model.addAttribute("cartCount", cartService.getCartItemCount(getSessionId(session)));
        return "books";
    }

    @GetMapping("/books/{id}")
    public String bookDetails(@PathVariable Long id, Model model, HttpSession session) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        
        model.addAttribute("book", book);
        model.addAttribute("cartCount", cartService.getCartItemCount(getSessionId(session)));
        return "book-details";
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {
        String sessionId = getSessionId(session);
        model.addAttribute("cartItems", cartService.getCartItems(sessionId));
        model.addAttribute("cartTotal", cartService.getCartTotal(sessionId));
        model.addAttribute("cartCount", cartService.getCartItemCount(sessionId));
        return "cart";
    }

    @PostMapping("/cart/add/{bookId}")
    public String addToCart(@PathVariable Long bookId,
                           @RequestParam(defaultValue = "1") Integer quantity,
                           HttpSession session) {
        cartService.addToCart(getSessionId(session), bookId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update/{itemId}")
    public String updateCartItem(@PathVariable Long itemId,
                                 @RequestParam Integer quantity) {
        cartService.updateQuantity(itemId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove/{itemId}")
    public String removeFromCart(@PathVariable Long itemId) {
        cartService.removeFromCart(itemId);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        String sessionId = getSessionId(session);
        model.addAttribute("cartItems", cartService.getCartItems(sessionId));
        model.addAttribute("cartTotal", cartService.getCartTotal(sessionId));
        model.addAttribute("cartCount", cartService.getCartItemCount(sessionId));
        return "checkout";
    }

    @GetMapping("/about")
    public String about(Model model, HttpSession session) {
        model.addAttribute("cartCount", cartService.getCartItemCount(getSessionId(session)));
        return "about";
    }
}
