package com.smartbookstore.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;

import com.smartbookstore.client.services.*;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/usermanagement")
public class UserDetailController {
    private UserDetailService userdetailService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String getAll(Model model){
        // model.addAttribute("isActive", "userdetail");
        // model.addAttribute("userdetails", userdetailService.getAll());
        return "user/index";
    }
}