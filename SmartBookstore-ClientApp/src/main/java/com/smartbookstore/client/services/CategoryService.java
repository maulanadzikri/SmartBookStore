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
public class CategoryService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.base.url}/category")
    private String url;
    
    // getAll
    public List<Category> getAll() {
        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Category>>() {}
        ).getBody();
    }
    
    // create new category
    public Category create(Category category) {
        return restTemplate.exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<Category>(category),
            new ParameterizedTypeReference<Category>() {}
        ).getBody();
    }

    // getById
    public Category getById(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Category>() {}
        ).getBody();
    }

    // category update
    public Category update(Integer id, Category category){
        HttpEntity<Category> request = new HttpEntity<Category>(category);
        return restTemplate.exchange(
            url.concat("/" + id), 
            HttpMethod.PUT, 
            request, 
            Category.class
        ).getBody();
    }


    // category delete
    public Category delete(Integer id){
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.DELETE,
            null,
            Category.class
        ).getBody();
    }    
}