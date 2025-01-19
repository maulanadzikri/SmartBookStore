package com.smartbookstore.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String transaction() {
        return "transaction/index";
    }
}
