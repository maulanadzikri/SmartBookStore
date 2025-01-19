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
public class PublisherService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.base.url}/publisher")
    private String url;
    
    // getAll
    public List<Publisher> getAll() {
        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Publisher>>() {}
        ).getBody();
    }
    
    // create new publisher
    public Publisher create(Publisher publisher) {
        return restTemplate.exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<Publisher>(publisher),
            new ParameterizedTypeReference<Publisher>() {}
        ).getBody();
    }

    // getById
    public Publisher getById(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Publisher>() {}
        ).getBody();
    }

    // publisher update
    public Publisher update(Integer id, Publisher publisher){
        HttpEntity<Publisher> request = new HttpEntity<Publisher>(publisher);
        return restTemplate.exchange(
            url.concat("/" + id), 
            HttpMethod.PUT, 
            request, 
            Publisher.class
        ).getBody();
    }


    // publisher delete
    public Publisher delete(Integer id){
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.DELETE,
            null,
            Publisher.class
        ).getBody();
    }    
}