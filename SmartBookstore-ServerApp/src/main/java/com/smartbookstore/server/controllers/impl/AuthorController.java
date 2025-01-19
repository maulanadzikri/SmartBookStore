package com.smartbookstore.server.controllers.impl;

import com.smartbookstore.server.controllers.GenericController;
import com.smartbookstore.server.model.entity.Author;
import com.smartbookstore.server.services.impl.AuthorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/author")
@PreAuthorize("hasRole('ADMIN')")
public class AuthorController implements GenericController<Author, Integer>{
    private AuthorService authorService;
    
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public List<Author> getAll(){
        return authorService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public Author getById(@PathVariable Integer id){
        return authorService.getById(id);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    public Author create(@RequestBody Author author){
        return authorService.create(author);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    public Author update(@PathVariable Integer id, @RequestBody Author author){
        return authorService.update(id, author);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    public Author delete(@PathVariable Integer id){
        return authorService.delete(id);
    }
}
