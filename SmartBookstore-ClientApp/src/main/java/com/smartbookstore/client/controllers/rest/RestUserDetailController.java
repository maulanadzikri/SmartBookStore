package com.smartbookstore.client.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.smartbookstore.client.models.UserDetail;
import com.smartbookstore.client.services.UserDetailService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/userdetail")
public class RestUserDetailController {
    private UserDetailService userDetailService;

    @GetMapping
    public List<UserDetail> getAll(){
        return userDetailService.getAll();
    }

    @GetMapping("/{id}")
    public UserDetail getById(@PathVariable Integer id){
        return userDetailService.getById(id);
    }

    @PutMapping("/{id}")
    public UserDetail update(@PathVariable Integer id, @RequestBody UserDetail userDetail) {
        return userDetailService.update(id, userDetail);
    }
}
