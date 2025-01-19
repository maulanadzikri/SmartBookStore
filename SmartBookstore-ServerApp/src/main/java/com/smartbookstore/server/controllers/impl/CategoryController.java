package com.smartbookstore.server.controllers.impl;

import com.smartbookstore.server.controllers.GenericController;
import com.smartbookstore.server.model.entity.Category;
import com.smartbookstore.server.services.impl.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/category")
@PreAuthorize("hasRole('ADMIN')")
public class CategoryController implements GenericController<Category, Integer>{
    private CategoryService categoryService;
    
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public Category getById(@PathVariable Integer id){
        return categoryService.getById(id);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    public Category update(@PathVariable Integer id, @RequestBody Category category){
        return categoryService.update(id, category);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    public Category delete(@PathVariable Integer id){
        return categoryService.delete(id);
    }
}
