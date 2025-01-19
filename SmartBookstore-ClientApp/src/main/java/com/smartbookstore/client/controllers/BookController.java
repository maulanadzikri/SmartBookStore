package com.smartbookstore.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartbookstore.client.models.Book;
import com.smartbookstore.client.models.dto.request.BookRequest;
import com.smartbookstore.client.services.*;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {
    private BookService bookService;
    private CategoryService categoryService;
    private AuthorService authorService;
    private PublisherService publisherService;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("isActive", "book");
        model.addAttribute("books", bookService.getAll());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        return "book/detail";
    }

    @GetMapping("/create")
    public String craeteView(BookRequest bookRequest, Model model) {
        model.addAttribute("categorys", categoryService.getAll());
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("publishers", publisherService.getAll());
        return "book/create";
    }

    @PostMapping
    public String create(BookRequest bookRequest) {
        bookService.create(bookRequest);
        return "redirect:/book";
    }
    
    @GetMapping("/update/{id}")
    public String updateView(@PathVariable Integer id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "book/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute BookRequest book) {
        bookService.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        bookService.delete(id);
        return "redirect:/book";
    }

    @GetMapping("/order/{id}")
    public String buyView(@PathVariable Integer id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "book/order";
    }
}