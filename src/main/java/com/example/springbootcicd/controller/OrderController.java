package com.example.springbootcicd.controller;

import com.example.springbootcicd.model.Order;
import com.example.springbootcicd.service.CartService;
import com.example.springbootcicd.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Order Web Controller - Handles order pages
 */
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    private String getSessionId(HttpSession session) {
        String sessionId = (String) session.getAttribute("CART_SESSION_ID");
        if (sessionId == null) {
            sessionId = session.getId();
            session.setAttribute("CART_SESSION_ID", sessionId);
        }
        return sessionId;
    }

    @PostMapping("/create")
    public String createOrder(@RequestParam String customerName,
                            @RequestParam String customerEmail,
                            HttpSession session,
                            Model model) {
        try {
            Order order = orderService.createOrder(getSessionId(session), customerName, customerEmail);
            return "redirect:/orders/confirmation/" + order.getId();
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "checkout";
        }
    }

    @GetMapping("/confirmation/{orderId}")
    public String orderConfirmation(@PathVariable Long orderId, Model model, HttpSession session) {
        Order order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        model.addAttribute("order", order);
        model.addAttribute("cartCount", cartService.getCartItemCount(getSessionId(session)));
        return "order-confirmation";
    }

    @GetMapping("/my-orders")
    public String myOrders(@RequestParam String email, Model model, HttpSession session) {
        model.addAttribute("orders", orderService.getOrdersByEmail(email));
        model.addAttribute("cartCount", cartService.getCartItemCount(getSessionId(session)));
        return "my-orders";
    }
}
