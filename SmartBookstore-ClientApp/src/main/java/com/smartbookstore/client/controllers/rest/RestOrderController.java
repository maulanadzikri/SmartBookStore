package com.smartbookstore.client.controllers.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.smartbookstore.client.models.Order;
import com.smartbookstore.client.models.dto.request.OrderRequest;
import com.smartbookstore.client.services.OrderService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class RestOrderController {
    private OrderService orderService;

    @GetMapping
    public List<Order> getAll(){
        return orderService.getAll();
    }

    @GetMapping("/history/all")
    public List<Order> getAllHistory(){
        return orderService.getAllHistory();
    }

    @GetMapping("/history/{id}")
    public List<Order> getById(@PathVariable Integer id){
        return orderService.getById(id);
    }

    @PostMapping
    public Order create(@RequestBody OrderRequest order) {
        return orderService.create(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody OrderRequest orderRequest) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Order updatedOrder = orderService.update(id, orderRequest);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public Order delete(@PathVariable Integer id) {
        return orderService.delete(id);
    }
}
