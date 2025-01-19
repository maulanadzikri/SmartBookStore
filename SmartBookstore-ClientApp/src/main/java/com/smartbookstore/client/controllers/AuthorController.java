package com.smartbookstore.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartbookstore.client.services.*;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private AuthorService authorService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("isActive", "author");
        model.addAttribute("authors", authorService.getAll());
        return "author/index";
    }
}
