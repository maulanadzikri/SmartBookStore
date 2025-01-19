package com.smartbookstore.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartbookstore.client.services.*;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/publisher")
public class PublisherController {
    private PublisherService publisherService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("isActive", "publisher");
        model.addAttribute("publishers", publisherService.getAll());
        return "publisher/index";
    }
}
