package com.smartbookstore.client.models.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Integer customerId;
    private List<BookOrderRequest> books;
    private String creditCard;
    private String cardNumber;
    private Integer mm;
    private Integer yy;
    private Integer cvv;
    private String cardHolderName;
}
