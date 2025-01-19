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
public class UserDetailService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.base.url}/userdetail")
    private String url;
    
    // getAll
    public List<UserDetail> getAll() {
        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<UserDetail>>() {}
        ).getBody();
    }

    // getById
    public UserDetail getById(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<UserDetail>() {}
        ).getBody();
    }

    // userDetail update
    public UserDetail update(Integer id, UserDetail userDetail){
        HttpEntity<UserDetail> request = new HttpEntity<UserDetail>(userDetail);
        return restTemplate.exchange(
            url.concat("/" + id), 
            HttpMethod.PUT, 
            request, 
            UserDetail.class
        ).getBody();
    }
}