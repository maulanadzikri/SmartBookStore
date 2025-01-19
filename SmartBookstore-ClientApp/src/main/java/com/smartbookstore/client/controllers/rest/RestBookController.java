package com.smartbookstore.client.controllers.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.smartbookstore.client.models.Book;
import com.smartbookstore.client.models.dto.request.BookRequest;
import com.smartbookstore.client.services.BookService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/book")
public class RestBookController {
    private BookService bookService;

    @GetMapping
    public List<Book> getAll(){
        return bookService.getAll();
    }

    @GetMapping("/history/{id}")
    public Book getById(@PathVariable Integer id){
        return bookService.getById(id);
    }

    @PostMapping
    public Book create(@RequestBody BookRequest book) {
        return bookService.create(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody BookRequest bookRequest) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Book updatedBook = bookService.update(id, bookRequest);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable Integer id) {
        return bookService.delete(id);
    }
}
