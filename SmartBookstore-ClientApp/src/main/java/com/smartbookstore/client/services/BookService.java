package com.smartbookstore.client.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.smartbookstore.client.models.*;
import com.smartbookstore.client.models.dto.request.BookRequest;

@Service
public class BookService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.base.url}/book")
    private String url;

    // getAll
    public List<Book> getAll() {
        List<Book> books = restTemplate.exchange(
            url.concat("/custom-all"),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Book>>() {}
        ).getBody();

        return books;
    }
    
    // create new book with dto
    public Book create(BookRequest bookRequest) {
        Book createdBook = restTemplate.exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<BookRequest>(bookRequest),
            new ParameterizedTypeReference<Book>() {}
        ).getBody();

        return createdBook;
    }

    // getById
    public Book getById(Integer id) {
        Book book = restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Book>() {}
        ).getBody();

        return book;
    }

    // book update
    public Book update(Integer id, BookRequest book){
        HttpEntity<BookRequest> request = new HttpEntity<BookRequest>(book);
        Book updatedBook = restTemplate.exchange(
            url.concat("/" + id), 
            HttpMethod.PUT, 
            request, 
            Book.class
        ).getBody();

        return updatedBook;
    }

    // book delete
    public Book delete(Integer id){
        Book deletedBook = restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.DELETE,
            null,
            Book.class
        ).getBody();
        
        return deletedBook;
    }
}

