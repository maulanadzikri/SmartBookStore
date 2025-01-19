package com.smartbookstore.server.controllers.impl;

import com.smartbookstore.server.controllers.GenericController;
import com.smartbookstore.server.model.dto.request.OrderRequest;
import com.smartbookstore.server.model.dto.response.OrderResponse;
import com.smartbookstore.server.model.entity.Order;
import com.smartbookstore.server.services.impl.OrderService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/order")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class OrderController implements GenericController<Order, Integer>{
    private OrderService orderService;
    
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public List<Order> getAll(){
        return orderService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public Order getById(@PathVariable Integer id){
        return orderService.getById(id);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public Order create(@RequestBody Order order){
        return orderService.create(order);
    }

    @PostMapping("/dto")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public OrderResponse createDTO(@RequestBody OrderRequest orderRequest) {
        return orderService.createDTO(orderRequest);
    }

    @GetMapping("/history/all")
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public List<Order> getAllOrderHistory() {
        return orderService.getAllOrderHistory();
    }

    @GetMapping("/history/{customerId}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public List<Order> getOrderHistory(@PathVariable Integer customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public Order update(@PathVariable Integer id, @RequestBody Order order){
        return orderService.update(id, order);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public Order delete(@PathVariable Integer id){
        return orderService.delete(id);
    }
}