package com.smartbookstore.server.services.impl;

import com.smartbookstore.server.model.dto.request.BookOrderRequest;
import com.smartbookstore.server.model.dto.request.OrderRequest;
import com.smartbookstore.server.model.dto.response.BookOrderResponse;
import com.smartbookstore.server.model.dto.response.OrderResponse;
import com.smartbookstore.server.model.entity.*;
import com.smartbookstore.server.repositories.OrderRepository;
import com.smartbookstore.server.services.GenericService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.text.SimpleDateFormat;  
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderService implements GenericService<Order, Integer>{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private BookService bookService;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!!!"));
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public OrderResponse createDTO(OrderRequest orderRequest) {
        Order order = new Order();
        UserDetail customer = userDetailService.getById(orderRequest.getCustomerId());
        int total = 0;
        List<Book> orderedBooks = new ArrayList<>();
        List<BookOrderResponse> bookResponses = new ArrayList<>();

        for (BookOrderRequest bookOrderRequest : orderRequest.getBooks()) {
            Book book = bookService.getById(bookOrderRequest.getBookId());
            int bookQty = bookOrderRequest.getBookQty();
            int bookPrice = book.getPrice();

            if (book.getStock() < bookQty) {
                throw new IllegalArgumentException("Stock tidak cukup: " + book.getTitle());
            }
            book.setStock(book.getStock() - bookQty);
            bookService.update(book.getId(), book);

            int bookTotalPrice = bookPrice * bookQty;
            total += bookTotalPrice;

            orderedBooks.add(book);
            bookResponses.add(new BookOrderResponse(book.getTitle(), bookQty, bookPrice));
        }

        Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
        String strDate = formatter.format(date);  

        order.setCustomer(customer);
        order.setBooks(orderedBooks);
        order.setTotal(total);
        order.setDate(strDate);
        Order saveOrder = orderRepository.save(order);

        return new OrderResponse(
            saveOrder.getId(),
            customer.getFullname(),
            total,
            strDate,
            bookResponses,
            orderRequest.getCreditCard(),
            orderRequest.getCardNumber(),
            orderRequest.getMm(),
            orderRequest.getYy(),
            orderRequest.getCvv(),
            orderRequest.getCardHolderName()
        );
    }

    public List<Order> getAllOrderHistory() {
        return orderRepository.findAll();
    }    

    public List<Order> getOrdersByCustomerId(Integer customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order update(Integer id, Order order) {
        getById(id);
        order.setId(id);
        return orderRepository.save(order);
    }

    @Override
    public Order delete(Integer id) {
        Order order = getById(id);
        orderRepository.delete(order);
        return order;
    }
}