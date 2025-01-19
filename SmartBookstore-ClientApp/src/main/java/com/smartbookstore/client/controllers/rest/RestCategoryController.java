package com.smartbookstore.client.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.smartbookstore.client.models.Category;
import com.smartbookstore.client.services.CategoryService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class RestCategoryController {
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Integer id){
        return categoryService.getById(id);
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Integer id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/{id}")
    public Category delete(@PathVariable Integer id) {
        return categoryService.delete(id);
    }
}
