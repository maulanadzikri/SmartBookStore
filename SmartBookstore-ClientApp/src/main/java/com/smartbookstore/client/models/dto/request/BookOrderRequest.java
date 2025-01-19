package com.smartbookstore.client.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookOrderRequest {
    private Integer bookId;
    private Integer bookQty;
}