package com.smartbookstore.client.models.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {
    private Integer id;
    private String username;
    private String name;
    private String email;
    private String phone;
    private List<String> authorities;
}
