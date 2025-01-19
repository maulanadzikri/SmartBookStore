package com.smartbookstore.server.controllers.impl;

import com.smartbookstore.server.controllers.GenericController;
import com.smartbookstore.server.model.dto.request.BookRequest;
import com.smartbookstore.server.model.dto.response.BookResponse;
import com.smartbookstore.server.model.entity.Book;
import com.smartbookstore.server.services.impl.BookService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class BookController implements GenericController<Book, Integer> {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/custom-all")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public List<Map<String, Object>> getAllCustom() {
        return bookService.getAllCustom();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public Book getById(@PathVariable Integer id) {
        return bookService.getById(id);
    }

    @Override
    @PostMapping("/dto")
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    public Book create(@RequestBody Book book) {
        return bookService.create(book);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    public Book createDTO(@RequestBody BookRequest bookRequest) {
      return bookService.createDTO(bookRequest);
    }

    @Override
    @PutMapping("/dto/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    public Book update(@PathVariable Integer id, @RequestBody Book updatedBook) {
        return bookService.update(id, updatedBook);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    public Book updateDTO(@PathVariable Integer id, @RequestBody BookRequest updatedBook) {
        return bookService.updateDTO(id, updatedBook);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    public Book delete(@PathVariable Integer id) {
        return bookService.delete(id);
    }
}
