package com.smartbookstore.server.services.impl;

import com.smartbookstore.server.model.dto.request.BookRequest;
import com.smartbookstore.server.model.dto.response.BookResponse;
import com.smartbookstore.server.model.entity.*;
import com.smartbookstore.server.repositories.BookRepository;
import com.smartbookstore.server.services.GenericService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookService implements GenericService<Book, Integer>{
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private UserDetailService userDetailService;

    private ModelMapper modelMapper;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<Map<String, Object>> getAllCustom() {
        return bookRepository
        .findAll()
        .stream()
        .map(book -> {
            Map<String, Object> result = new HashMap<>();
            result.put("id", book.getId());
            result.put("title", book.getTitle());
            result.put("year", book.getYear());
            result.put("price", book.getPrice());
            result.put("img", book.getImg());
            result.put("stock", book.getStock());
            result.put("categoryId", book.getCategory().getId());
            result.put("categoryName", book.getCategory().getName());
            result.put("authorId", book.getAuthor().getId());
            result.put("authorName", book.getAuthor().getName());
            result.put("publisherId", book.getPublisher().getId());
            result.put("publisherName", book.getPublisher().getName());
            return result;
        })
        .collect(Collectors.toList());
    }

    @Override
    public Book getById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found!!!"));
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book createDTO(BookRequest bookRequest) {
        Book book = new Book();
        Category category = categoryService.getById(bookRequest.getCategory());
        Author author = authorService.getById(bookRequest.getAuthor());
        Publisher publisher = publisherService.getById(bookRequest.getPublisher());
        UserDetail admin = userDetailService.getById(bookRequest.getAdmin());

        book.setTitle(bookRequest.getTitle());
        book.setYear(bookRequest.getYear());
        book.setPrice(bookRequest.getPrice());
        book.setStock(bookRequest.getStock());
        book.setImg(bookRequest.getImg());
        book.setCategory(category);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setAdmin(admin);

        return bookRepository.save(book);
    }

    @Override
    public Book update(Integer id, Book book) {
        getById(id);
        book.setId(id);
        return bookRepository.save(book);
    }

    public Book updateDTO(Integer id, BookRequest bookRequest) {
        Book book = getById(id);
        Category category = categoryService.getById(bookRequest.getCategory());
        Author author = authorService.getById(bookRequest.getAuthor());
        Publisher publisher = publisherService.getById(bookRequest.getPublisher());
        UserDetail admin = userDetailService.getById(bookRequest.getAdmin());

        book.setTitle(bookRequest.getTitle());
        book.setYear(bookRequest.getYear());
        book.setPrice(bookRequest.getPrice());
        book.setStock(bookRequest.getStock());
        book.setImg(bookRequest.getImg());
        book.setCategory(category);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setAdmin(admin);

        return bookRepository.save(book);
    }

    @Override
    public Book delete(Integer id) {
        Book book = getById(id);
        bookRepository.delete(book);
        return book;
    }
}

