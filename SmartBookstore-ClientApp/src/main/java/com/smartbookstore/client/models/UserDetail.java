package com.smartbookstore.client.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {
    private Integer id;
    private String fullname;
    private String email;
    private String phone;
}
