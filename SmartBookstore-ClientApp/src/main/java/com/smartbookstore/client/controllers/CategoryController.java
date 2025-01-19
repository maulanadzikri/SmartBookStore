package com.smartbookstore.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smartbookstore.client.services.*;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("isActive", "category");
        model.addAttribute("categorys", categoryService.getAll());
        return "category/index";
    }
}
