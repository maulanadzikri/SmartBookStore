package com.smartbookstore.client.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookOrderResponse {
    private String title;
    private Integer bookQty;
    private Integer bookPrice;
}