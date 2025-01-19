package com.smartbookstore.server.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Integer id;
    private String title;
    private Integer year;
    private Integer price;
    private String img;
    private Integer stock;
    private Integer categoryId;
    private String categoryName;
    private Integer authorId;
    private String authorName;
    private Integer publisherId;
    private String publisherName;
}
