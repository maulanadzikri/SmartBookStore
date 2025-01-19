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
import com.smartbookstore.client.models.dto.request.OrderRequest;

@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.base.url}/order")
    private String url;

    // getAll
    public List<Order> getAll() {
        List<Order> orders = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Order>>() {}
        ).getBody();

        return orders;
    }

    public List<Order> getAllHistory() {
        List<Order> orders = restTemplate.exchange(
            url.concat("/history/all"),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Order>>() {}
        ).getBody();

        return orders;
    }
    
    // create new order with dto
    public Order create(OrderRequest orderRequest) {
        Order createdOrder = restTemplate.exchange(
            url.concat("/dto"),
            HttpMethod.POST,
            new HttpEntity<OrderRequest>(orderRequest),
            new ParameterizedTypeReference<Order>() {}
        ).getBody();

        return createdOrder;
    }

    // getById
    public List<Order> getById(Integer id) {
        List<Order> order = restTemplate.exchange(
            url.concat("/history/" + id),
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Order>>() {}
        ).getBody();

        return order;
    }

    // order update
    public Order update(Integer id, OrderRequest order){
        HttpEntity<OrderRequest> request = new HttpEntity<OrderRequest>(order);
        Order updatedOrder = restTemplate.exchange(
            url.concat("/" + id), 
            HttpMethod.PUT, 
            request, 
            Order.class
        ).getBody();

        return updatedOrder;
    }

    // order delete
    public Order delete(Integer id){
        Order deletedOrder = restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.DELETE,
            null,
            Order.class
        ).getBody();
        
        return deletedOrder;
    }
}
