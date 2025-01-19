package com.smartbookstore.client.models;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private Customer customer;
    private String date;
    private Integer total;
    private List<BookOrder> books;
}