package com.smartbookstore.client.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.smartbookstore.client.models.Book;
import com.smartbookstore.client.models.dto.request.BookRequest;
import com.smartbookstore.client.models.dto.request.LoginRequest;
import com.smartbookstore.client.models.dto.request.OrderRequest;
import com.smartbookstore.client.models.dto.response.LoginResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartbookstore.client.services.*;
import com.smartbookstore.client.utils.AuthSessionsUtil;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private BookService bookService;
    private OrderService orderService;
    private AuthService authService;

    @GetMapping("/order/{id}")
    public String buyView(@PathVariable Integer id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);

        Authentication auth = AuthSessionsUtil.getAuthentication();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(auth.getName());
        loginRequest.setPassword(auth.getCredentials().toString());
        LoginResponse loginResponse = authService.getLoginUser(loginRequest);
        model.addAttribute("userId", loginResponse.getId());

        return "book/order";
    }

    @PostMapping
    public String create(OrderRequest orderRequest) {
        orderService.create(orderRequest);
        return "index";
    }

    @GetMapping
    public String orderView(Model model) {
        return orderData(model, "book/order");
    }

    private String orderData(Model model, String viewName) {
        Authentication auth = AuthSessionsUtil.getAuthentication();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(auth.getName());
        loginRequest.setPassword(auth.getCredentials().toString());

        LoginResponse loginResponse = authService.getLoginUser(loginRequest);
        if (loginResponse != null) {
            model.addAttribute("userId", loginResponse.getId());
            return viewName;
        } else {
            return "redirect:/login?error=true";
        }
    }
}
