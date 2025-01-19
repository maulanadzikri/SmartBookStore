package com.smartbookstore.server.services.impl;

import com.smartbookstore.server.model.entity.Category;
import com.smartbookstore.server.repositories.CategoryRepository;
import com.smartbookstore.server.services.GenericService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoryService implements GenericService<Category, Integer> {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!!!")
            );
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Integer id, Category category) {
        getById(id);
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public Category delete(Integer id) {
        Category category = getById(id);
        categoryRepository.delete(category);
        return category;
    }
}
