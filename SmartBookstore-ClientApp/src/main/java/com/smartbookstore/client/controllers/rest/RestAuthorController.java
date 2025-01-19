package com.smartbookstore.client.controllers.rest;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.smartbookstore.client.models.Author;
import com.smartbookstore.client.services.AuthorService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/author")
public class RestAuthorController {
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAll(){
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable Integer id){
        return authorService.getById(id);
    }

    @PostMapping
    public Author create(@RequestBody Author author) {
        return authorService.create(author);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Integer id, @RequestBody Author author) {
        return authorService.update(id, author);
    }

    @DeleteMapping("/{id}")
    public Author delete(@PathVariable Integer id) {
        return authorService.delete(id);
    }
}
