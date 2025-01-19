package com.smartbookstore.server.model.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer id;
    private String customerName;
    private Integer total;
    private String date;
    private List<BookOrderResponse> books;
    private String creditCard;
    private String cardNumber;
    private Integer mm;
    private Integer yy;
    private Integer cvv;
    private String cardHolderName;
}
