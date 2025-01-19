package com.smartbookstore.client.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookOrder {
    private Integer id;
    private String title;
    private Integer year;
    private Integer price;
    private String img;
    private Author author;
    private Category category;
    private Publisher publisher;
}
