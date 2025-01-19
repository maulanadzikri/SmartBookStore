package com.smartbookstore.client.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String title;
    private Integer year;
    private Integer price;
    private String img;
    private Integer stock;
    private Integer category;
    private Integer author;
    private Integer publisher;
    private Integer admin;
}
