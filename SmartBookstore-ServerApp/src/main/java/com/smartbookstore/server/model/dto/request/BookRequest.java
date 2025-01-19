package com.smartbookstore.server.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private String title;
    private Integer year;
    private Integer price;
    private Integer stock;
    private String img;
    private Integer category;
    private Integer author;
    private Integer publisher;
    private Integer admin;
}
