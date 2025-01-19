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

@Service
public class AuthorService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.base.url}/author")
    private String url;

    // getAll
    public List<Author> getAll() {
        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Author>>() {}
        ).getBody();
    }
    
    // create new author
    public Author create(Author author) {
        return restTemplate.exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<Author>(author),
            new ParameterizedTypeReference<Author>() {}
        ).getBody();
    }

    // getById
    public Author getById(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Author>() {}
        ).getBody();
    }

    // author update
    public Author update(Integer id, Author author){
        HttpEntity<Author> request = new HttpEntity<Author>(author);
        return restTemplate.exchange(
            url.concat("/" + id), 
            HttpMethod.PUT, 
            request, 
            Author.class
        ).getBody();
    }


    // author delete
    public Author delete(Integer id){
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.DELETE,
            null,
            Author.class
        ).getBody();
    }    
}